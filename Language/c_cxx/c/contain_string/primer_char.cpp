#include <iostream>

using namespace std;

bool StringContain(string &a, string &b)
{
    const int p[26] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 27, 41,
                       43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 97, 101};
    int f = 1;
    for (int i = 0; i < a.length(); ++i) {
        int x = p[a[i] - 'A'];
        if (f % x) {
            f *= x;
        }
    }
    for (int i = 0; i < b.length(); ++i) {
        int x = p[b[i] - 'A'];
        if (f % x) {
            return false;
        }
    }
    return true;
}
