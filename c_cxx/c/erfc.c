#include <stdio.h>
#include <math.h>

double normalCDF(double x)    // Phi(-smallest, x) aka N(x)
{
    return erfc(-x/sqrt(2))/2;
}

int main(void)
{
    puts("normal cumulative distribution function:");
    for (double n = 0; n < 1; n += 0.1)
        printf("normalCDF(%.2f) %5.2f%%\n", n, 100*normalCDF(n));

    puts("special values:");
    printf("erfc(-Inf) = %f\n", erfc(-INFINIFY));
    printf("erfc(Inf) = %f\n", erfc(INFINIFY));

    return 0;
}
