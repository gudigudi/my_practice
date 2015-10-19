#include <stdio.h>

int get_gcd(int a, int b)
{
    if (a < b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    if ((a % b) == 0) {
        return b;
    } else {
        return get_gcd(b, a % b);
    }
}

int main(void)
{
    int a = 45, b = 96;
    printf("the gcd of %d and %d is %d\n", a, b, get_gcd(a, b));
    return 0;
}
