#include <stdio.h>
#include <math.h>

double phi(double x1, double x2)
{
    return (erf(x2/sqrt(2)) - erf(x1/sqrt(2))) / 2;
}

int main(void)
{
    puts("normal variate probabilities:");
    for (int n = 0; n < 4; ++n)
        printf("[%2d:%2d]: %5.2f%%\n", n, n+1, 100*phi(n, n+1));

    puts("special values:");
    printf("erf(-0) = %f\n", erf(-0.0));
    printf("erf(Inf) = %f\n", erf(INFINITY));

    return 0;
}
