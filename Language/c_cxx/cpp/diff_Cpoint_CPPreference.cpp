#include <iostream>

using namespace std;

// Does not work
void swapWrong( int a, int b)
{
    int tmp = a;
    a = b;
    b = a;
}

// C style -- using pointers.
void swapPtr( int *a, int *b )
{
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

// C++ Style -- using reference
void swapRef( int &a, int &b )
{
    int tmp = a;
    a = b;
    b = tmp;
}

int main()
{
    int x = 5, y = 7;

    swapWrong( x, y );
    cout << "x=" << x << " y=" << y << endl;
    swapPtr( &x, &y );
    cout << "x=" << x << " y=" << y << endl;
    swapRef( x, y );
    cout << "x=" << x << " y=" << y << endl;

    return 0;
}
