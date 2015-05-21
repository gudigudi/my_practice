#include <stack>
#include <iostream>
#include <vector>

using namespace std;

int main()
{
    stack<int, vector<int> > s;

    s.push(1);
    s.push(2);
    s.push(3);

    cout << "Contents are: " << endl;
    for ( ; !s.empty(); s.pop()) {
        cout << s.top() << endl;
    }
}
