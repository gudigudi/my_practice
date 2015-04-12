#include <stdio.h>
#include <math.h>
#include <fenv.h>

#pragma STDC FENV_ACCESS ON

int main(void)
{
    printf("fmod(+5.1, +3.0) = %.1f\n", fmod(5.1, 3));
    return 0;
}
