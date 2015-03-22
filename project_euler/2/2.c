#include <stdio.h>

int main(void)
{
    unsigned long int a = 1;
    unsigned long int b = 2;
    unsigned long int sum = a + b;
    unsigned long int tmp_b;

    while (b < 4000000) {
        tmp_b = b;
        b = a + tmp_b;
        a = tmp_b;
        sum += b;
    }

    printf("sum is: %lu\n", sum);

    return 0;
}
