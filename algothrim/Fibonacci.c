/*
 *
 * Test Fibonacci
 */
#include <stdio.h>

long Fibonacci(long n)
{
    return n < 2 ? n : Fibonacci(n-1) + Fibonacci(n-2);
}

int main(void)
{
    for (long i = 0; i < (1 << 6); ++i) {
        printf("%16ld\n", Fibonacci(i));
    }
    return 0;
}

