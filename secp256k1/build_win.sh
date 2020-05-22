./docker/dockcross-windows-x64 -a --rm bash -c './autogen.sh && JAVA_HOME=`pwd`/src/java/jniheaders ./configure --host=x86_64-w64-mingw32 --enable-experimental --enable-module_ecdh --enable-module-recovery --enable-jni && make clean && make'

