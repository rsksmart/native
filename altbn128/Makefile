CGO_CFLAGS = "-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -I$(JAVA_HOME)/include/darwin"

.PHONY: clean build test

linux-amd64:
	CGO_ENABLED=1 CGO_CFLAGS=$(CGO_CFLAGS) GOOS=linux GOARCH=amd64 go build -trimpath -ldflags '-s -tmpdir /tmp' -buildmode=c-shared -o amd64/libbn128.so precompile.go

linux-arm64:
	CGO_ENABLED=1 CGO_CFLAGS=$(CGO_CFLAGS) GOOS=linux GOARCH=arm64 CC=aarch64-linux-gnu-gcc go build -trimpath -ldflags '-s -tmpdir /tmp' -buildmode=c-shared -o arm64/libbn128.so precompile.go

macos:
	CGO_ENABLED=1 CGO_CFLAGS=$(CGO_CFLAGS) GOOS=darwin GOARCH=amd64 go build -trimpath -buildmode=c-shared -o libbn128.dylib -v precompile.go

windows:
	CGO_ENABLED=1 CGO_CFLAGS=$(CGO_CFLAGS) GOOS=windows GOARCH=amd64 CC="x86_64-w64-mingw32-gcc" go build -trimpath -buildmode=c-shared -o libbn128.dll -v precompile.go

test:
	CGO_CFLAGS=$(CGO_CFLAGS) go test -bench=. ./...

clean:
	rm -f amd64/libbn128.so arm64/libbn128.so libbn128.dylib libbn128.h libbn128.dll
