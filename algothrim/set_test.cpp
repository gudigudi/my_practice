

#include <iostream>
#include <string>
#include <set>

using namespace std;

int main()
{
    typedef set<int, greater<int> > my_set;
    const int SIZE = 8;
    const string HEADING = "Here are the items in the set:";
    const string REPEAT = " is already in the set. Insertion rejected.";
    const string CLOSE_WINDOW_PROMPT =
        "Please press the Enter key to close this output window.";

    my_set s;
    my_set::iterator itr;
    int data[SIZE] = {5, 3, 9, 3, 7, 2, 9, 3};

    for (int i = 0; i < SIZE; ++i) {
        pair< my_set::iterator, bool> p = s.insert(data[i]);
        if (!p.second)
            cout << data[i] << REPEAT << endl;
    }

    cout << endl << HEADING << endl;
    for (itr = s.begin(); itr != s.end(); itr++) {
        cout << *itr << endl;
    }

    cout << endl << CLOSE_WINDOW_PROMPT;
    cin.get();

	return 0;
}
