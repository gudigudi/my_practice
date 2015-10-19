#include <stdio.h>
#include <values.h>

int main(void)
{
    printf("Storage size for float : %lu\n", sizeof(float));
    printf("minimum float positive value: %E\n", FLT_MIN);
    printf("Maximum float positive value: %E\n", FLT_MAX);
    printf("Precision value: %d\n", FLT_DIG);

    return 0;
}
