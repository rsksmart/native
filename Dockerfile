FROM ubuntu:18.04

# CUSTOM VARIABLES
ENV GOCUSTOM=/usr/local
ENV GOLANG=go1.13.5.linux-amd64.tar.gz
ENV ALTBN128_LINUX_RESOURCES=src/main/resources/co/rsk/altbn128/cloudflare/native/linux
ENV ALTBN128_LINUX_LIBRARY=libbn128.so
ENV SECP256K1_LINUX_RESOURCES=src/main/resources/org/bitcoin/native/Linux/x86_64
ENV SECP256K1_LINUX_LIBRARY=libsecp256k1.so

# ENV VARIABLES
ENV GOROOT=$GOCUSTOM/go
ENV GOPATH=/native/altbn128
ENV GOBIN=$GOPATH/bin
ENV PATH=$GOPATH/bin:$GOROOT/bin:$PATH
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CGO_CFLAGS="-I$JAVA_HOME/include -I$JAVA_HOME/include/linux"

RUN apt-get update && \
 apt-get -y install git && \
 apt-get -y install tree && \
 apt-get install -y -o APT::Install-Suggests="false" git curl openjdk-8-jdk build-essential=12.4ubuntu1 && \
 apt-get install -y -o APT::Install-Suggests="true" autoconf && \
 curl "https://dl.google.com/go/"$GOLANG -o $GOLANG -# && \
 echo "512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569  go1.13.5.linux-amd64.tar.gz" > goChecksum.txt && \
 cat goChecksum.txt && \
 sha256sum -c goChecksum.txt && \
 tar -xvf $GOLANG && mkdir -p $GOCUSTOM && mv go $GOCUSTOM

#Cloning native's repo
#RUN git clone https://github.com/rsksmart/native.git
COPY . /native
WORKDIR /native

# Copying jni headers to java /include
RUN cp -r jniheaders/include $JAVA_HOME

# Building altbn128
WORKDIR /native/altbn128
RUN go get &&\
  make clean &&\
  make linux &&\
  rm -f ../$ALTBN128_LINUX_RESOURCES/$ALTBN128_LINUX_LIBRARY &&\
  cp $ALTBN128_LINUX_LIBRARY ../$ALTBN128_LINUX_RESOURCES

# Expose
WORKDIR /native
ENTRYPOINT ["./gradlew"]
CMD ["buildProject"]
