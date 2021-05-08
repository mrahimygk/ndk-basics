//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainStringActivity_ndkGetHello(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("THIS IS JNI SPEAKING");
}