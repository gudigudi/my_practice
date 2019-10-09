#include <jni.h>
#include <string>

#include "Log.h"
#include "RandomStringGenerator.h"


extern "C"
JNIEXPORT jstring JNICALL
Java_com_gudigudigudi_nativelib_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject instance) {
    char TAG[] = "com_gudigudigudi_nativelib_NativeLib";

    Log::Verbose(TAG, "JNI %s", "com_gudigudigudi_nativelib_NativeLib_stringFromJNI");

    std::string hello = getRandomString(10);
    return env->NewStringUTF(hello.c_str());
}