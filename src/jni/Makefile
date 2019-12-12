CGO_CFLAGS = "-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux"
NATIVE_PATH = "../main/resources/com/bakaoh/altbn128/cloudflare/native"
.PHONY: clean build test

linux:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-shared -o libbn128.so precompile.go
	mkdir -p $(NATIVE_PATH)/linux
	cp libbn128.so $(NATIVE_PATH)/linux/libbn128.so

macos:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-shared -o libbn128.dylib precompile.go
	mkdir -p $(NATIVE_PATH)/macos
	cp libbn128.dylib $(NATIVE_PATH)/macos/libbn128.dylib

test:
	CGO_CFLAGS=$(CGO_CFLAGS) go test -bench=. ./...

clean:
	rm -f libbn128.so libbn128.dylib
