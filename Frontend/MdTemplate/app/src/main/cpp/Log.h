//
// Created by gu on 9/19/17.
//

#ifndef MDTEMPLATE_LOG_H
#define MDTEMPLATE_LOG_H

#include <cstdarg>
#include <android/log.h>
#include <string>

using std::string;

namespace Log {

    void Verbose(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_VERBOSE, TAG.c_str(), format, args);
        va_end(args);
    }

    void Debug(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_DEBUG, TAG.c_str(), format, args);
        va_end(args);
    }

    void Info(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_INFO, TAG.c_str(), format, args);
        va_end(args);
    }

    void Warn(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_WARN, TAG.c_str(), format, args);
        va_end(args);
    }

    void Error(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_ERROR, TAG.c_str(), format, args);
        va_end(args);
    }

    void Fatal(string TAG, const char *format, ...) {
        va_list args;

        va_start(args, format);
        __android_log_vprint(ANDROID_LOG_FATAL, TAG.c_str(), format, args);
        va_end(args);
    }


};

#endif //MDTEMPLATE_LOG_H
