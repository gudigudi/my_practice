#include <stdio.h>
#include <math.h>

double myround(double x)
{
    if(x > 0.0) {
        return floor(x);
    } else {
        return floor(x);
    }
}

int main(void)
{
    double d[3] = {-3.51, 4.49, 0.0};
    for (int i = 0; i < 3; ++i) {
        printf("myround of %lf is %lf\n", d[i], myround(d[i]));
    }
    return 0;
}
