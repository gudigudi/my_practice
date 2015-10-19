#include <complex.h>
#include <stdio.h>

int main(void)
{
    printf("float complex size=%lu\n", sizeof((float complex)1.0));
    printf("double complex size=%lu\n", sizeof((double complex)1.0));
    printf("long double complex size=%lu\n", sizeof((long double complex)1.0));

    return 0;
}
