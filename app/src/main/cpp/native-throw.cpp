//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainNativeCallActivity_ndkThrow(JNIEnv *env, jclass clazz,
                                                                 jobject exceptionist_class) {

    jclass exceptionistClass = env->GetObjectClass(exceptionist_class);
    jmethodID doTimeTravel = env->GetMethodID(
            exceptionistClass,
            "doTimeTravel",
            "()V");

    env->CallVoidMethod(exceptionist_class, doTimeTravel);
    jthrowable exception = env->ExceptionOccurred();
    if (exception != nullptr) {
        env->ExceptionClear();

        jclass eClazz = env->GetObjectClass(exception);
        jclass runtimeExceptionClass = env->FindClass("java/lang/RuntimeException");

        jmethodID getMessage = env->GetMethodID(eClazz,
                                                "getMessage",
                                                "()Ljava/lang/String;");
        auto message = (jstring) env->CallObjectMethod(exception, getMessage);
        const char *messagePrefix = "Natives can throw exceptions";
        const char *messageString = env->GetStringUTFChars(message, nullptr);
        char result[1000];
        strcpy(result, messagePrefix);
        strcat(result, "\n");
        strcat(result, messageString);

        env->ThrowNew(runtimeExceptionClass, result);
        return nullptr;
    }

    return env->NewStringUTF("Wow! you have avoided the exception!");

}