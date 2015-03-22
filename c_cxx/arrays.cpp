#include <iostream>
#include <numeric>
#include <iterator>

void g(int (&a)[3])
{
    std::cout << a[0] << '\n';
}

void f(int* p)
{
    int a[3] = {1, 2, 3};
    int* p = a;

    std::cout << sizeof a << '\n'  // prints size of array
              << sizeof p << '\n'; // prints size of a pointer

    // where arrays are acceptable, but pointers aren't, only arrays may be used
    g(a);  // OK: function takes an array by reference

    for (int n: a)
        std::cout << n << ' ';

    std::iota(std::begin(a), std::end(a), 7);

    f(a);
    f(p);

    std::cout << *a << '\n'
              << *p << '\n'
              << *(a+1) << ' ' << a[1] << '\n'
              << *(p+1) << ' ' << p[1] << '\n';
}
