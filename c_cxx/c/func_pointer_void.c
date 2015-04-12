#include <stdio.h>
#include <stdlib.h>

#define NUM 5

int MyCompare( const void * elem1, const void * elem2)
{
    unsigned int * p1, * p2;
    p1 = (unsigned int *)elem1; // elem1指针是void类型,需要转换为int类型
                                // 方可赋值
    p2 = (unsigned int *)elem2;
    return (*p1 % 10) - (*p2 % 10);
}

int main(void)
{
    unsigned int an[NUM] = {8, 123, 11, 10, 4};
    qsort(an, NUM, sizeof(unsigned int), MyCompare);
    for (int i = 0; i < NUM; ++i)
    {
        printf("%d ", an[i]);
    }
    return 0;
}
