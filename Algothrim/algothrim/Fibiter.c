/*
 * Fibter, a better Fibonacci algorithm
 */
#include <stdio.h>

long Fibter(long n)
{
    long i = 1, j = 0;
    for (long k = 0; k < n; ++k) {
        j = i + j;
        i = j - i;
    }
    return j;
}

int main(void)
{
    for (long i = 0; i < (1 << 16); ++i) {
        printf("%16ld\n", Fibter(i));
    }
    return 0;
}
