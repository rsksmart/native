./autogen.sh 
 mkdir -p /System/Library/Frameworks/JavaVM.framework/Headers 
 cp src/java/jniheaders/include/jni.h src/java/jniheaders/include/mac/jni_md.h /System/Library/Frameworks/JavaVM.framework/Headers/ 
./configure --host=x86_64-w64-darwin --enable-experimental --enable-module_ecdh --enable-module-recovery --enable-jni 
 make clean 
 make
