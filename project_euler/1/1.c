#include <stdio.h>

int main(void)
{
    const int a = 3;
    const int b = 5;
    const int upper_num = 1000;
    int sum = 0;

    for (int i = 1; i*a < upper_num; i++) {
        sum += i * a;
    }
    for (int j = 1; j*b < upper_num; ++j) {
        sum += j*b;
    }

    printf("sum is: %d\n", sum);

    return 0;
}
