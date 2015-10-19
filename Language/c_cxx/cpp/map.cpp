#include <iostream>
#include <map>
#include <string>

using namespace std;

int main()
{
    map<string, int ,less<string> > people;

    people["Tim"] = 3;
    people["Bob"] = 5;
    cout << "Tim's value is " << people["Tim"] << endl;
    cout << "Bob's value is " << people["Bob"] << endl;

	return 0;
}
