#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

/**
 * C++ has namespace to manager scope better!
 */
namespace First {
    namespace Nested {
        void foo()
        {
            printf("This is First::Nested::foo\n");
        }
    }
}
namespace Second {
    void foo()
    {
        printf("This is Second::foo\n");
    }
}
void foo()
{
    printf("This is global foo\n");
}

int main()
{
    // sizeof char in C++ is 1
    cout << sizeof('c') << '\n';

    // namespace in C++
    Second::foo();
    First::Nested::foo();
    ::foo();

    // string in C++
    string myString = "Hello";
    string myOtherString = " World";
    cout << myString + myOtherString;
    cout << myString + " You";
    myString.append(" Dog");
    cout << myString;

    // reference are better point
    string foo = "I am foo";
    string bar = "I am bar";

    string& fooRef = foo;
    fooRef += ". Hi!";
    cout << fooRef;

    // after it, foo is changed to bar
    fooRef = bar;
    cout << foo;



    return 0;
}
