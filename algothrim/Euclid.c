/*
 * test Euclid algorithm
 */

#include <stdio.h>

int Euclid(int small, int big)
{
    while(small > 0) {
        int t = small;
        small = big % small;
        big = t;
    }
    return big;
}

int main(void)
{
    int small = 5, big = 210;
    printf("%d\n", Euclid(small, big));
    return 0;
}
