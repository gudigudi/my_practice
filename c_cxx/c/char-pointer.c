#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void)
{
    char x = 'a';
    char *p = NULL;  // 定义一个字符指针
    p = &x;  // 取x变量的地址值
    printf("x的值:%c\n", *p);  // 输出x的值
    printf("x的地址值:%d\n", p);  // 输出x的地址
    *p = 'z';  // 将x变量的值改变为z
    printf("x被改变:%c\n", x);  // 输出x的值

    return 0;
}
