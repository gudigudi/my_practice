#include <stdio.h>

struct _struct1 {
    int i;
    double d;
} struct1 { .i = 1, .d = 0.1 };

int main(void)
{
    struct1 strc1, strc2;
    printf("%d\t%lf\n", strc1.i,);
    return 0;
}
