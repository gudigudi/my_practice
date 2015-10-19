// Ａsimple time class

#ifndef CPPTIME_H
#define CPPTIME_H

#include <ctime>
#include <cstring>

class Time
{
    std::time_t t;
    std::tm local;
    char asciiRep[26];
    unsigned char lflag, aflag;
    void updateLocal()
    {
        if (!lflag) {
        local = *std::localtime(&t);
        lflag++;
        }
    }
  public:
    Time() { mark(); }
    void mark()
    {
   　   lflag = aflag = 0;
        std::time(&t);
    }
    const char* ascii()
    {
        updateAscii();
        return asciiRep;
    }
    int delta(Time* dt) const
    {
        return int(std::difftime(t, dt->t));
    }
    int daylightSavings()
    {
        updateLocal();
        return local.tm_isdst;
    }
    int dayOfYear()
    {
        updateLocal();
        return local.tm_yday;
    }
    int dayOfWeek()
    {
        updateLocal();
        return local.tm_wday;
    }
    int  since1900()
    {
        updateLocal();
        return local.tm_year;
    }
    int month()
    {
        updateLocal();
        return local.tm_mon;
    }

};

#endif
