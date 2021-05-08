//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainNativeCallActivity_ndkLock(JNIEnv *env, jclass clazz,
                                                                jobject activity) {

    if (JNI_OK != env->MonitorEnter(activity)) {
        return env->NewStringUTF("Error in MonitorEnter");
    }

    jclass cls = env->GetObjectClass(activity);
    jmethodID mid = env->GetMethodID(cls, "getClass", "()Ljava/lang/Class;");
    jobject clsObj = env->CallObjectMethod(activity, mid);
    cls = env->GetObjectClass(clsObj);
    mid = env->GetMethodID(cls, "getName", "()Ljava/lang/String;");
    auto strObj = (jstring) env->CallObjectMethod(clsObj, mid);

    if (JNI_OK != env->MonitorExit(activity)) {
        return env->NewStringUTF("Error in MonitorExit");
    }

    return strObj;
}