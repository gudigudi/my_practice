#include <iostream>

using namespace std;

int main(int argc, char *argv[])
{
    int myArray[10];
    cout << "Enter 10 integers, press [ENTER] "
        << "after every integer.\n";
    for (int i = 0; i < 10; ++i) {
        cin >> myArray[i];
        cout << myArray[i];
    }
    return 0;
}
