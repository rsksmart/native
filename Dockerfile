FROM ubuntu:18.04

ENV GOLANG=go1.13.5.linux-amd64.tar.gz
ENV GOROOT=/go
ENV GOBIN=$GOROOT/bin
ENV GOPATH=/root/go
ENV PATH=$GOBIN:$PATH
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV CGO_CFLAGS="-I$JAVA_HOME/include -I$JAVA_HOME/include/linux"

RUN apt-get update -y && \
    apt-get install -y git curl openjdk-8-jdk=8u232-b09-0ubuntu1~18.04.1 build-essential && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get autoremove -y && \
    apt-get clean

WORKDIR /code/bn256
ADD src/jni/ ./

RUN curl "https://dl.google.com/go/"$GOLANG -o $GOLANG -# && \
    echo "512103d7ad296467814a6e3f635631bd35574cab3369a97a323c9a585ccaa569  go1.13.5.linux-amd64.tar.gz" > shasum.txt && \
    cat shasum.txt
RUN sha256sum -c shasum.txt

RUN tar -xvf $GOLANG
RUN mkdir $GOROOT && mv go /

RUN go get && make clean && make linux && make macos
RUN sha256sum libbn128.dylib && \
	sha256sum libbn128.so

