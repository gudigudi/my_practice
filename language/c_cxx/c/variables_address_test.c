#include <stdio.h>

int main(void)
{
    // Define some integer variables
    long a = 1L;
    long b = 2L;
    long c = 3L;

    // Define some floating-point variables
    double d = 4.0;
    double e = 5.0;
    double f = 6.0;

    printf("A variable of type long occupies %lu bytes.\n", sizeof(long));
    printf("Here are the addresses of some variables of type long:\n");
    printf("The address of a is: %p\n   \
            The address of b is: %p\n   \
            The address of c is: %p\n", &a, &b, &c);
    printf("\nA variable of type double occupies %lu bytes.\n", sizeof(double));
    printf("Here are the addresses of some variables of type double:\n");
    printf("The address of d is: %p\n   \
            The address of e is: %p\n   \
            The address of f is: %p\n", &d, &e, &f);

    return 0;
}
