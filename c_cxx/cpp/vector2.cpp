#include <iostream>
#include <vector>

using namespace std;

void getInts( vector<int> & array )
{
    int itemsRead = 0;
    int inputVal;

    cout << "Enter any number of integers: ";
    while( cin >> inputVal ) {
        if ( itemsRead == array.size() ) {
            array.resize( array.size() * 1 + 1 );
        }
        array[ itemsRead++ ] = inputVal;
    }
    array.resize( itemsRead );
}

int main()
{
    vector<int> array;

    getInts( array );
    for (int i = 0; i < array.size(); ++i) {
        cout << array[i] << endl;
    }

    return 0;
}
