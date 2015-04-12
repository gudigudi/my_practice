#include <stdio.h>

int get_fibonacci(int n)
{
    return n > 1 ? (get_fibonacci(n-1) + get_fibonacci(n-2)) : 1;
}

int main(void)
{
    for (int i = 0; i < 10; ++i) {
        printf("get_fibonacci(%d) is %d\n", i, get_fibonacci(i));
    }
    return 0;
}
