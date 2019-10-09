
#include <cstdlib>
#include <string>

#ifndef MDTEMPLATE_RANDOMSTRINGGENERATOR_H
#define MDTEMPLATE_RANDOMSTRINGGENERATOR_H

using namespace std;

static const char alphanum[] =
        "0123456789"
                "abcdefghijklmnopqrstuvwxyz"
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

int stringLength = sizeof(alphanum) - 1;

char getRandomChar() {
    return alphanum[std::rand() % stringLength];
}

std::string getRandomString(int length) {
    std::string randomString;

    for (int i = 0; i < length; ++i) {
        randomString += getRandomChar();
    }
    return randomString;
}

#endif //MDTEMPLATE_RANDOMSTRINGGENERATOR_H
