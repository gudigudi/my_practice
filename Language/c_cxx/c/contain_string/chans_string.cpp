/*
 * insert '#' to a string : between every char
 */

#include <iostream>

using namespace std;

string get_chans_string(string &s)
{
    string s2 = "#";
    for (int i = 0; i < s.length(); ++i) {
        s2 += s[i] + "#";
    }
    return s2;
}

int main(int argc, char *argv[])
{
    string s;
    cout << "Enter a string: " << '\n';
    cin >> s;

    cout << get_chans_string(s) << '\n';

    return 0;
}
