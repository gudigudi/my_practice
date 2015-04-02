/*
 * Fibter, a better Fibonacci algorithm
 */
#include <stdio.h>

int Fibter(int n)
{
    int i = 1, j = 0;
    for (int k = 0; k < n; ++k) {
        j = i + j;
        i = j - i;
    }
    return j;
}

int main(void)
{
    for (int i = 0; i < (1 << 5); ++i) {
        printf("%10d\n", Fibter(i));
    }
    return 0;
}
