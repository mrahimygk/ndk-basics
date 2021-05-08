//
// Created by vincent on 5/8/21.
//

#include<cstring>
#include<jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_ir_mrahimy_myndkapplication_MainNativeCallActivity_ndkCallMe(JNIEnv *env, jclass clazz,
                                                                  jobject phone_number) {

    jstring pJstring;
    jclass phoneNumberClass = env->GetObjectClass(phone_number);
    jmethodID getPhone = env->GetMethodID(
            phoneNumberClass,
            "getPhone",
            "()Ljava/lang/String;");

    pJstring = (jstring) (env->CallObjectMethod(phone_number, getPhone));
    const char *strReturn = env->GetStringUTFChars(pJstring, 0);


    env->ReleaseStringUTFChars(pJstring, strReturn);


    const char *str1 = "OK I will call this phone number ";

    char result[1000];

    strcpy(result, str1);
    strcat(result, "\n");
    strcat(result, strReturn);

    return env->NewStringUTF(result);


}