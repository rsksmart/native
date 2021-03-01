FROM ubuntu:18.04

# ENV VARIABLES
ENV GOLANG=go1.13.5.linux-amd64.tar.gz
ENV GOROOT=/usr/local/go
ENV GOPATH=/native/altbn128
ENV GOBIN=$GOPATH/bin
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CGO_CFLAGS="-I$JAVA_HOME/include -I$JAVA_HOME/include/linux"
ENV PATH=$GOPATH/bin:$GOROOT/bin:$PATH

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
