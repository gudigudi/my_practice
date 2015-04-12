#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main(void)
{
    char strs[5] = {'a', 'b', 'c', 'd', 'e'};
    char *pstrs = NULL;  // 定义一个字符数组指针
    pstrs = strs;  // 数组本身就是指向数组元素第一个值的内存的地址
    printf("数组第一个值:%c 数组第二个值:%c\n", *pstrs, *(pstrs + 1));
    // 如果指针指向地址改变，则指向数组的值也改变
    pstrs++;
    printf("\n数组第一个值:%c\n", *pstrs);
    printf("\n数组也可以通过下标方式获取值:%c\n", strs[3]);
    // 还可以把数组变量直接当初指针来操作
    int i;
    printf("\n循环输入:");
    for (i = 0; i < sizeof(strs) / sizeof(char); ++i) {
        printf("\n:%c", *(strs + i));
    }

    return 0;
}
