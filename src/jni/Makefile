CGO_CFLAGS = "-I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux"
ARCHIVE_FILE = libbn128.a
SHARED_FILE = libbn128.so

.PHONY: clean build test

build:
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-archive -o $(ARCHIVE_FILE) precompile.go
	CGO_CFLAGS=$(CGO_CFLAGS) go build -buildmode=c-shared -o $(SHARED_FILE) precompile.go

test:
	CGO_CFLAGS=$(CGO_CFLAGS) go test -bench=. ./...

clean:
	rm -f $(ARCHIVE_FILE) $(SHARED_FILE)
