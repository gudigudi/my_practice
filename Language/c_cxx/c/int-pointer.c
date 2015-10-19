#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void)
{
    int a = 100;
    int *p = NULL;  // 定义一个int类型指针
    p = &a;  // &号取地址值, &a就是取a的地址值
    printf("a的值:%d\n", *p);  // *是取值符号，*p就是取a的值
    *p = 1001;  // 改变a的值为1001
    printf("a值被改变:%d\n", a);  // 输出a的值
    printf("a的地址值:%d\n", p);  // 直接p即地址值

    return 0;
}
