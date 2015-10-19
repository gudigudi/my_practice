#include <stdio.h>
#include <stdlib.h>

struct S {
    char c;
    double d;
};

int main(void)
{
    printf("the first element is at offset %zu\n", offsetof(struct S, c));
    printf("the double is at offset %zu\n", offsetof(struct S, d));
    return 0;
}
