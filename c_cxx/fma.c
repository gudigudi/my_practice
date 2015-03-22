#include <stdio.h>
#include <math.h>
#include <values.h>
#include <fenv.h>

#pragma STDC FENV_ACCESS ON

int main(void)
{
    // demo the difference between fma and built-in operators
    double in = 0.1;
    printf("0.1 double is %.23f (%a)\n", in, in);

    return 0;
}
