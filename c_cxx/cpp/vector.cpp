#include <stdlib.h>
#include <iostream>
#include <vector>

using namespace std;

// Generate numbers (from 1-100)
// Print number of occurrences of each number.
int main()
{
    const int DIFFERENT_NUMBERS = 100;
    int totalNumbers;
    cout << "How many numbers to generate?: ";
    cin >> totalNumbers;

    vector<int> numbers( DIFFERENT_NUMBERS + 1);
    for (int i = 0; i < numbers.size(); ++i) {
        numbers[i] = 0;
    }

    for (int i = 0; i < totalNumbers; ++i) {
        numbers[ rand() % DIFFERENT_NUMBERS + 1]++;
    }

    for (int i = 0; i <= DIFFERENT_NUMBERS; ++i) {
        cout << i << " occurs " << numbers[ i ] << " time(s)\n";
    }

    return 0;
}
