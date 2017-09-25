#include <jni.h>
#include <string>

#include "Log.h"
#include "RandomStringGenerator.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    char TAG[]="com_gudigudigudi_mdtemplate_MainActivity";

    Log::Verbose(TAG, "JNI %s", "com_gudigudigudi_mdtemplate_MainActivity_stringFromJNI");

    std::string hello = getRandomString(10);
    return env->NewStringUTF(hello.c_str());

}

JNIEXPORT void JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_nativeInit(JNIEnv *env, jobject instance) {
}

JNIEXPORT void JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_nativeFree(JNIEnv *env, jobject instance) {
}