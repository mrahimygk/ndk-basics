//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jobject JNICALL
Java_ir_mrahimy_myndkapplication_MainNativeCallActivity_ndkMutable(JNIEnv *env, jclass clazz,
                                                                   jobject data_class) {

    jclass dataClass = env->GetObjectClass(data_class);
    jmethodID setFname = env->GetMethodID(
            dataClass,
            "setFname",
            "(Ljava/lang/String;)V");

    env->CallVoidMethod(data_class, setFname, env->NewStringUTF("Jane"));
    return data_class;
}