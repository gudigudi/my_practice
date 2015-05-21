#include <iostream>
#include <set>
#include <functional>
#include <string>

using namespace std;

int main()
{
    set<string, greater<string> > s;

    s.insert("joe");
    s.insert("bob");

    return 0;
}
