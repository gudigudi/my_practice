#include <iostream>

int main(void)
{
    unsigned long a = 1;
    unsigned long b = 2;
    unsigned long sum = a + b;
    unsigned long tmp_b;

    while (b < 4000000) {
        tmp_b = b;
        b = a + tmp_b;
        a = tmp_b;

        sum += b;
    }

    std::cout << "sum is: " << sum << std::endl;

    return 0;
}
