CGO_CFLAGS = "-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux"
.PHONY: clean build test

linux:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -gcflags=all=-trimpath=/var/folders -asmflags=all=-trimpath=/var/folders  -buildmode=c-shared -o libbn128.so precompile.go

macos:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -gcflags=all=-trimpath=/var/folders -asmflags=all=-trimpath=/var/folders -buildmode=c-shared -o libbn128.dylib precompile.go

test:
	CGO_CFLAGS=$(CGO_CFLAGS) go test -bench=. ./...

clean:
	rm -f libbn128.so libbn128.dylib libbn128.h