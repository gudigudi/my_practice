#include <stdio.h>

int main(void)
{
    unsigned long num                  = 600851475143;
    unsigned long largest_prime_factor = 1;

    unsigned long i = 2;

    while(num % i) {
        i++;
    }
    largest_prime_factor = num / i;

    printf("largest_prime_factor is: %lu\n", largest_prime_factor);

    return 0;
}
