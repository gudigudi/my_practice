#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

// 定义一个结构体
struct student {
    char *username;
    int age;
};

int main(void)
{
    struct student *p = NULL;  // 定义一个结构体指针
    // 分配一个结构体内存，p指向这个内存地址
    p = (struct student *) malloc(sizeof(struct student));
    p->username = "zhuli";
    p->age = 100;
    printf("结构体数据:用户名%s 用户年龄%d\n", p->username, p->age);

    return 0;
}
