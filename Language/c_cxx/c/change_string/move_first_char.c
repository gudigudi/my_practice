/*
 * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部
 */

#include <stdio.h>
#include <string.h>

/*
 * 暴力式
 * 获取第一个字符, 将之后的字符向前移动一位，再将第一个字符赋值到最后一位
 */

void move_left_one(char * str, int n)
{
    char tmp_char = str[0];
    printf("%c\n", tmp_char);
    for (int i = 0; i < n; ++i) {
        str[i] = str[i+1];
        printf("1:\t%s\n", str);
    }
    str[n-1] = tmp_char;
    printf("%c\n", str[n]);
    printf("2:\t%s\n", str);
}

void move_left_to_right(char * str, int n, int m)
{
    while(m--) {
        move_left_one(str, n);
    }
}

int main(void)
{
    char str[] = "hello, gudi!";
    printf("Before move!\t%s\n", str);
    int line = 4;
    move_left_to_right(str, strlen(str), line);

    printf("After move!\t%s\n", str);
    return 0;
}
