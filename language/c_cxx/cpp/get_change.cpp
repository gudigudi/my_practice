/*
 * get change : 100, 50, 20, 10, 5, 1
 */
#include <iostream>

using namespace std;

int main()
{
    int numOf100, numOf50, numOf20, numOf10, numOf5, numOf1;
    int money;
    cout << "Enter the number of money: ";
    cin >> money;

    numOf100 = money / 100;
    money %= 100;
    numOf50 = money / 50;
    money %= 50;
    numOf20 = money / 20;
    money %= 20;
    numOf10 = money / 10;
    money %= 10;
    numOf5 = money / 5;
    money %= 5;
    numOf1 = money;

    cout << "number of 100\t" << numOf100 << "\n"
        << "number of 50\t" << numOf50 << "\n"
        << "number of 20\t" << numOf20 << "\n"
        << "number of 10\t" << numOf10 << "\n"
        << "number of 5\t" << numOf5 << "\n"
        << "number of 1\t" << numOf1 << endl;

    return 0;
}



