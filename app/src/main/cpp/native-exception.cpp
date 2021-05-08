//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainStringActivity_ndkException(JNIEnv *env, jclass clazz,
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
        jmethodID getMessage = env->GetMethodID(eClazz,
                                                "getMessage",
                                                "()Ljava/lang/String;");
        auto message = (jstring) env->CallObjectMethod(exception, getMessage);
        const char *mstr = env->GetStringUTFChars(message, nullptr);

        char result[1000];
        strcpy(result, "An Exception occurred ");
        strcat(result, "\n");
        strcat(result, mstr);
        return env->NewStringUTF(result);
    }

    return env->NewStringUTF("Wow! you have traveled time successfully!");

}