#include <jni.h>
#include <string>

#include "Log.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    char TAG[]="com_gudigudigudi_mdtemplate_MainActivity";
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());

    Log::Verbose(TAG,"JNI %s","com_gudigudigudi_mdtemplate_MainActivity_stringFromJNI");
}

JNIEXPORT void JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_nativeInit(JNIEnv *env, jobject instance) {



}

JNIEXPORT void JNICALL
Java_com_gudigudigudi_mdtemplate_MainActivity_nativeFree(JNIEnv *env, jobject instance) {


}