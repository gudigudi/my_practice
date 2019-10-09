//
// Created by gu on 9/19/17.
//

#ifndef MDTEMPLATE_ECHO_H
#define MDTEMPLATE_ECHO_H

#include <jni.h>
#include <cstdio>
#include <linux/signal.h>
#include <cstring>
#include <cerrno>

#define MAX_LOG_MESSAGE_LENGTH 256 // 最大日志消息长度

#define MAX_BUFFER_SIZE 80 // 最大数据缓冲区大小


/**
 * 将给定的消息记录到引用程序.
 * @param env
 * @param obj
 * @param format
 */
static void LogMessage(JNIEnv *env, jobject obj, const char *format, ...) {
    static jmethodID methodID = nullptr; // 缓存日志ID

    if (nullptr == methodID) { // 如果方法id未缓存
        jclass clazz = env->GetObjectClass(obj); // 从对象获取类
        methodID = env->GetMethodID(clazz, "logMessage", "(Ljava/lang/String;)V"); // 从给定方法获取方法ID
        env->DeleteLocalRef(clazz); // 释放类引用
    } else { // 如果找到方法
        char buffer[MAX_LOG_MESSAGE_LENGTH]; // 格式化日志消息

        va_list ap;
        va_start(ap, format);
        vsnprintf(buffer, MAX_LOG_MESSAGE_LENGTH, format, ap);
        va_end(ap);

        jstring message = env->NewStringUTF(buffer); // 将缓冲去转换为Java字符串

        if (nullptr != message) { // 如果字符串构造正确
            env->CallVoidMethod(obj, methodID, message); // 记录消息
            env->DeleteLocalRef(message); // 释放消息引用
        }
    }
}

/**
 * 用给定的异常类和异常消息抛出新的异常
 * @param env
 * @param className
 * @param message
 */
static void ThrowException(JNIEnv *env, const char *className, const char *message) {
    jclass clazz = env->FindClass(className); // 获取异常类
    if (nullptr != clazz) { // 如果异常类未找到
        env->ThrowNew(clazz, message); // 抛出异常
        env->DeleteLocalRef(clazz); // 释放原生类引用
    }
}

/**
 * 用给定异常类和基于错误号的错误消息抛出新异常
 * @param env
 * @param className
 * @param errnum
 */
static void ThrowException(JNIEnv *env, const char *className, int errnum) {
    char buffer[MAX_LOG_MESSAGE_LENGTH];

    if (-1 == strerror_r(errnum, buffer, MAX_LOG_MESSAGE_LENGTH)) { // 获取错误号消息
        strerror_r(errno, buffer, MAX_LOG_MESSAGE_LENGTH);
    }

    ThrowException(env, className, buffer); // 抛出异常
}


#endif //MDTEMPLATE_ECHO_H
