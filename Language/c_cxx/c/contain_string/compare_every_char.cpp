/* see compare_every_char.c
 * CPP have string type for this problem
 */

#include <iostream>
#include <string>

using namespace std;

bool StringContain(string &a, string &b)
{
    for (int i = 0; i < b.length(); ++i) {
        int j;
        for (j = 0; j < a.length() && a[j] != b[j]; ++j) {
            ;
        }
        if (j >= a.length()) {
            return false;
        }
    }
    return true;
}

int main(int argc, char *argv[])
{
    cout << StringContain("ABC", "ABCDDE") << '\n';

    return 0;
}
