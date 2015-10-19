#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned long long uint64;
typedef unsigned int       DWORD;
typedef unsigned char      uchar;

static uint64 ValueTmp;

int f2f(uint64 *Value)
{
    uchar i, tmp;
    uint64 tmpSave = 0;

    memcpy(&ValueTmp, Value, sizeof(uint64));

    for (i = 0; i < sizeof(uint64); ++i) {
        tmp = (ValueTmp >> (i * 8)) & 0xff;
        tmp = (tmp & 0x0F) << 4 | (tmp & 0xF0) >> 4;
        tmpSave |= (((uint64)tmp & 0xFF) << (i * 8));
    }

    memcpy(Value, &tmpSave, sizeof(uint64));

    return 0;
}

int t2t(uint64 *Value)
{
    uchar i, tmp;
    uint64 tmpSave = 0;

    memcpy(&ValueTmp, Value, sizeof(uint64));

    for (i = 0; i < 2 * sizeof(uint64); ++i) {
        tmp = (ValueTmp >> (i * 4)) & 0x0f;
        tmp = (tmp & 0x03) << 2 | (tmp & 0x0c) >> 2;
        tmpSave |= (((uint64)tmp & 0x0f) << (i * 4));
    }

    memcpy(Value, &tmpSave, sizeof(uint64));

    return 0;
}

int main(void)
{
    uint64 value = 0xabcdef1234567890;
    uint64 value2 = 0xabcdef1234567890;

    printf("value: 0x%16llx\n", value);
    f2f(&value);
    printf("value: 0x%16llx\n", value);
    f2f(&value);
    printf("value: 0x%16llx\n", value);

    //
    printf("value2: 0x%16llx\n", value2);
    t2t(&value2);
    printf("value2: 0x%16llx\n", value2);
    t2t(&value2);
    printf("value2: 0x%16llx\n", value2);

    return 0;
}
