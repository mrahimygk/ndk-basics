//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainStringActivity_ndkGetHello(JNIEnv *env, jclass clazz,
                                                                jobject activity) {

    const char *str1 = "THIS IS JNI SPEAKING";
    const char *str2 = "You are calling me from an activity named ";

    char result[1000];

    strcpy(result, str1);
    strcat(result, "\n");
    strcat(result, str2);
    strcat(result, "\n");

    jclass cls = env->GetObjectClass(activity);
    jmethodID mid = env->GetMethodID(cls, "getClass", "()Ljava/lang/Class;");
    jobject clsObj = env->CallObjectMethod(activity, mid);
    cls = env->GetObjectClass(clsObj);
    mid = env->GetMethodID(cls, "getName", "()Ljava/lang/String;");
    auto strObj = (jstring)env->CallObjectMethod(clsObj, mid);
    const char* strName = env->GetStringUTFChars(strObj, nullptr);

    strcat(result, strName);

    env->ReleaseStringUTFChars(strObj, strName);
    return env->NewStringUTF(result);
}