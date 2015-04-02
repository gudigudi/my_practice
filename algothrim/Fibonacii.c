/*
 *
 * Test Fibonacci
 */
#include <stdio.h>

int Fibonacci(int n)
{
    return n < 2 ? n : Fibonacci(n-1) + Fibonacci(n-2);
}

int main(void)
{
    for (int i = 0; i < (1 << 5); ++i) {
        printf("%10d\n", Fibonacci(i));
    }
    return 0;
}

