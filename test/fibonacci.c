// fibonacci.c

#include <stdio.h>


int fibonacci(int n);

int main(void)
{
    printf("%d\n", fibonacci(41));

    return 0;
}

int fibonacci(int n)
{
    if (n < 2)
        return 1;
    return fibonacci(n-2) + fibonacci(n-1);
}
