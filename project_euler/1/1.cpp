#include <iostream>

int main(void)
{
    const int a = 3;
    const int b = 5;
    const int upper_num = 1000;
    int sum = 0;

    for (int i = 1; i*a < upper_num; ++i) {
        sum += i*a;
    }
    for (int i = 1; i*b < upper_num; ++i) {
        sum += i*b;
    }

    std::cout << "sum is: " << sum << std::endl;

    return 0;
}
