//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>
#include <pthread.h>
#include <cstdio>
#include <malloc.h>

void *long_function(void *args);

struct args {
    JNIEnv *env;
    jobject intWrapper;
    JavaVM *jvm;
};

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainNativeCallActivity_ndkThread(JNIEnv *env, jclass clazz,
                                                                  jobject intWrapper) {

    JavaVM *jvm;
    auto *input = (struct args *) malloc(sizeof(struct args));
    input->env = env;
    input->intWrapper = intWrapper;
    env->GetJavaVM(&jvm);
    input->jvm = jvm;

    pthread_t t;
    jint threadInitCode = pthread_create(&t, nullptr, long_function, input);

    char buf[64];
    sprintf(buf, "%d", threadInitCode);

    char result[100];
    const char *msg = "Thread created with code ";
    strcpy(result, msg);
    strcat(result, buf);

    jclass mClazz = env->GetObjectClass(intWrapper);
    jmethodID getInt = env->GetMethodID(mClazz, "getInt", "()I");
    jmethodID setInt = env->GetMethodID(mClazz, "setInt", "(I)V");
    jint ret = env->CallIntMethod(intWrapper, getInt) + 1;
    env->CallVoidMethod(intWrapper, setInt, ret);

    return env->NewStringUTF(result);
}

void *long_function(void *inArgs) {
    auto args = (struct args *) inArgs;
    auto *env = args->env;
    auto intWrapper = args->intWrapper;
    auto jvm = args->jvm;
    jvm->AttachCurrentThread(&env, nullptr);

    while (true) {
        /*jclass clazz = env->GetObjectClass(intWrapper);
        jmethodID getInt = env->GetMethodID(clazz, "getInt", "()I");
        jmethodID setInt = env->GetMethodID(clazz, "setInt", "(I)V");
        jint ret = env->CallIntMethod(intWrapper, getInt) + 1;
        env->CallVoidMethod(intWrapper, setInt, ret);*/
    }
    return nullptr;
}
