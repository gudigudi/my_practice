#include <iostream>

using namespace std;

string *stupid()
{
    string s = "stupid";
    return &s;
}

int main(int argc, char *argv[])
{
    cout << *stupid() << endl;
    return 0;
}
