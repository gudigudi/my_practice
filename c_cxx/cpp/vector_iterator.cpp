#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> words;
    for (int i = 0; i < 10; ++i) {
        words.push_back(i);
    }
    for (vector<int>::const_iterator iter = words.begin();
         iter != words.end(); iter++) {
        cout << "gugu" << endl;
    }
	return 0;
}
