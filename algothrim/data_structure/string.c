#include "string.h"

size_t strlen(const char *str)
{
    const char *s;
    for (s = str; *s; ++s) {}
    return (s - str);
}

char *strcpy(char *to, const char *from)
{
    assert(to != NULL && from != NULL);
    char *p = to;
    while((*p++ = *from++) != '\0') {}
    return to;
}

char *strstr(const char *haystack, const char *needle)
{
    // if needle is empty return the full string
    if (!*needle) {
        return (char*) haystack;
    }

    const char *p1, *p2, *p1_advance = haystack;
    for (p2 = &needle[1]; *p2; ++p2) {
        p1_advance++;    // advance p1_advance M-1 times
    }

    for (p1 = haystack; *p1_advance; p1_advance++) {
        char *p1_old = (char*)p1;
        p2 = needle;
        while(*p1 && *p2 && *p1 == *p2) {
            p1++;
            p2++;
        }
        if (!*p2) return p1_old;

        p1 = p1_old + 1;
    }
    return NULL;
}
