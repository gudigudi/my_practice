/*
 * 翻转子字符串
 */

#include <stdio.h>
#include <string.h>

/*
 * 翻转字符串
 */
void ReverseString(char * str, int from, int to)
{
    while(from < to) {
        char tmpChar = str[from];
        str[from++] = str[to];
        str[to--] = tmpChar;
    }
}

void move_by_reverse_substring(char * str, int movelen, int strlen)
{
    ReverseString(str, 0, movelen-1);
    ReverseString(str, movelen, strlen-1);
    ReverseString(str, 0, strlen-1);
}

int main(void)
{
    char str[] = "hello, world";
    printf("%s\n", str);
    int movelen = 4;
    move_by_reverse_substring(str, movelen, strlen(str));
    printf("%s\n", str);
    return 0;
}
