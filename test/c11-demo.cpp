#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

int main(int argc, char *argv[])
{
    vector<int> iv = {1, 2, 3, 4, 5};    // 新的初始化方式
    unordered_map<string, int> people = {
        {"zhangsan", 12},
        {"lisi", 21},
        {"wangwu", 18}
    };
    for_each(iv.begin(), iv.end(), [](int x){cout << x << " ";});

    cout << '\n';

    for (auto per : people) {
        cout << per.first << ":" << per.second << "\n";
    }

    cout << endl;

    return 0;
}
