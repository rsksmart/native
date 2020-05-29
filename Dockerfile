FROM ubuntu:18.04

ENV GOLANG=go1.13.5.linux-amd64.tar.gz
ENV GOROOT=/go
ENV GOBIN=$GOROOT/bin
ENV GOPATH=/root/go
ENV PATH=$GOBIN:$PATH
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CGO_CFLAGS="-I$JAVA_HOME/include -I$JAVA_HOME/include/linux"

RUN apt-get update && \
	apt-get install -y -o APT::Install-Suggests="false" git curl openjdk-8-jdk build-essential=12.4ubuntu1
RUN apt-get install -y -o APT::Install-Suggests="true" autoconf

WORKDIR /code/bn256
ADD src/jni/ ./

RUN curl "https://dl.google.com/go/"$GOLANG -o $GOLANG -# && \
    echo "512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569  go1.13.5.linux-amd64.tar.gz" > shasum.txt && \
    cat shasum.txt
RUN sha256sum -c shasum.txt

RUN tar -xvf $GOLANG
RUN mkdir $GOROOT && mv go /

RUN go get && make clean && make linux
RUN sha256sum libbn128.so

WORKDIR /code/secp256k1
ADD secp256k1/ ./
RUN export JAVA_HOME=$(pwd)/src/java/jniheaders
RUN echo $JAVA_HOME && ./autogen.sh &&\
    ./configure --enable-experimental --enable-module_ecdh --enable-module-recovery --enable-jni &&\
    make clean &&\
    make
RUN sha256sum .libs/libsecp256k1.so.0.0.0


