/*
 * 查找字符串A是否包含字符串B
 */

#include <stdio.h>
#include <string.h>

/* 声明string数据变量 */
typedef struct _string {
    int length;
    char * str;
} string;

int length(string s)
{
    return strlen(s.str);
}

/*
 * 按单个字符逐一查找
 */
bool StringContain(string &a, string &b)
{
    for (int i = 0; i < length(b); ++i) {
        int j;
        for (j = 0; j < length(a) && a.str[j] != b.str[j]; j++) {
            ;
        }
        if (j >= length(a)) {
            return false;
        }
    }
    return true;
}

int main(void)
{

    return 0;
}
