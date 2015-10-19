#include <stdio.h>

int main()
{
    // sizeof char in C is 4, but if you use g++, that's 1 like c++
    printf("%ld\n", sizeof('c'));
    return 0;
}
