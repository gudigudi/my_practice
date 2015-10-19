#include <iostream>

int main(void)
{
    unsigned long num = 600851475143;
    unsigned long smallest_prime_factor = 2;

    while(num % smallest_prime_factor != 0) {
        smallest_prime_factor++;
    }

    std::cout << "largest_prime_factor is: "
        << num / smallest_prime_factor << std::endl;

    return 0;
}
