FROM ubuntu:18.04

# ENV VARIABLES
ENV GOLANG=go1.13.5.linux-amd64.tar.gz
ENV GOROOT=/usr/local/go
ENV GOPATH=/native/altbn128
ENV GOBIN=$GOPATH/bin
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CGO_CFLAGS="-I$JAVA_HOME/include -I$JAVA_HOME/include/linux"
ENV PATH=$GOPATH/bin:$GOROOT/bin:$PATH

# This variable makes __DATE__ & __TIME__ constant (needed for gcc deterministic compile)
# makes eth_pairings.dll reproducible, therefore the whole project
ENV SOURCE_DATE_EPOCH=1612383945

# DEPENDENCIES

# common
RUN apt-get update
RUN apt-get install -y build-essential
RUN apt-get install -y -o APT::Install-Suggests="false" git tree curl openjdk-8-jdk

# secp25k1 dependencies
RUN apt-get install -y autoconf libtool

# altbn128 dependencies
RUN curl "https://dl.google.com/go/"$GOLANG -o $GOLANG -# && \
 echo "512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569  $GOLANG" > goChecksum.txt && \
 cat goChecksum.txt && \
 sha256sum -c goChecksum.txt && \
 tar -xvf $GOLANG && \
 mkdir -p /usr/local && \
 mv go /usr/local

# bls12-381 dependencies
RUN curl "https://sh.rustup.rs" -sSf | bash -s -- -y
RUN apt-get install -y clang gcc g++ zlib1g-dev libmpc-dev libmpfr-dev libgmp-dev wget cmake libxml2-dev libssl-dev gcc-mingw-w64-x86-64
RUN git clone https://github.com/tpoechtrager/osxcross && \
 cd osxcross && \
 wget -nc https://s3.dockerproject.org/darwin/v2/MacOSX10.10.sdk.tar.xz&& \
 mv MacOSX10.10.sdk.tar.xz tarballs/ && \
 UNATTENDED=yes OSX_VERSION_MIN=10.7 ./build.sh

RUN apt-get install -y vim

# Cloning native's repo
COPY . /native
WORKDIR /native

# Copying jni headers to java /include
RUN cp -r jniheaders/include $JAVA_HOME

# Expose
WORKDIR /native
ENTRYPOINT ["./gradlew"]
CMD ["buildProject"]
