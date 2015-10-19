#include <iostream>
#include <algorithm>
#include <string>
#include <cctype>    // int std::toupper(int)
#include <locale>    // template<class CharT> CharT std::toupper(CharT, const locale&)

int f(int)    { return 1; }
int f(double) { return 2; }

void g( int(&f1)(int), int(*f2)(double) ) {}

template< int((*F)(int)) >
struct Templ {};

struct Foo {
    int mf(int)    { return 3; }
    int mf(double) { return 4; }
}

// 5. return value
int (*(foo)(void))(int) {
    return f;    // selects int f(int)
}

int main(void)
{
    // 1. initialization
    int (*pf)(double) = f;    // selects int f(double)
    int (&rf)(int)    = f;    // selects int f(int)
    int (Foo::*mpf)(int) = &Foo::mf;    // selects int mf(int)

    // 2. assignment
    pf = nullptr;
    pf = &f;    // selects int f(double)

    // 3. function argument
    g(f, f);    // selects int f(int) for the 1st argument
                // and int f(double) for the second
    // 4. user-defined operator

    // 6. cast
    std::string str = "example";
    std::transform(str.begin(), str.end(), str.begin(),
                   static_cast<int(*)(int)>(std::toupper));  // selects int toupper(int)
    std::cout << str << '\n';

    // 7. template argument
    Templ<f> t;  // selects int f(int)

    return 0;
}
