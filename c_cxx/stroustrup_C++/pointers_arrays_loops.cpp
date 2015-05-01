#include <iostream>

using namespace std;

void copy_fct()
{
    int v1[10];
    int v2[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    for (auto i = 0; i < 10; ++i) {
        v1[i] = v2[i];
    }
}

void print()
{
    int v[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    for (auto x : v)  cout << x << "\n";
    for (auto x : {10, 21, 32, 43, 54, 65})
        cout << x << '\n';
}

void increment()
{
    int v[] = {0,1,2,3,4,5,6,7,8,9};

    for (auto& x : v)  ++x;
}

int main()
{
    print();

    increment();
    return 0;
}
