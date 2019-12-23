CGO_CFLAGS = "-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux"
NATIVE_PATH = "../main/resources/co/rsk/altbn128/cloudflare/native"
.PHONY: clean build test

linux:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-shared -o libbn128.so precompile.go
	mkdir -p $(NATIVE_PATH)/linux
	mv libbn128.so $(NATIVE_PATH)/linux/libbn128.so
	mv libbn128.h $(NATIVE_PATH)/linux/libn128.h

macos:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-shared -o libbn128.dylib precompile.go
	mkdir -p $(NATIVE_PATH)/macos
	mv libbn128.dylib $(NATIVE_PATH)/macos/libbn128.dylib
	mv libbn128.h $(NATIVE_PATH)/macos/libn128.h

test:
	CGO_CFLAGS=$(CGO_CFLAGS) go test -bench=. ./...

