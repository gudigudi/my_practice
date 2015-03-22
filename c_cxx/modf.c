#include <stdio.h>
#include <math.h>

int main(void)
{
    double x, fractpart, intpart;

    x = 8.1234567;
    fractpart = modf(x, &intpart);

    printf("%lf\n", intpart);
    printf("%lf\n", fractpart);

    return 0;
}
