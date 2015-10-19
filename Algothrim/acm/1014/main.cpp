#include <vector>
#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

struct Student
{
    Student(const string& _id): id(_id), tot(0) {}
    string id;
    int tot;
};

bool score_cmp(const Student& a, const Student& b)
{
    if (a.tot != b.tot) {
        return a.tot > b.tot;
    } else {
        return a.id < b.id;
    }
}

int N, M, G, score[11];
int m, num;
string id;

int main(void)
{
    while(cin >> N, N) {
        vector<Student> vt;
        cin >> M >> G;
        for (int i = 0; i < M; ++i) {
            cin >> score[i];
        }
        for (int i = 0; i < N; ++i) {
            cin >> id >> m;
            Student student(id);
            while(m--) {
                cin >> num;
                student.tot += score[num];
            }
            if (student.tot >= G) {
                vt.push_back(student);
            }
        }
        sort(vt.begin, vt.end(), score_cmp);
        cout << vt.size() << endl;
        for (size_t i = 0; i < vt.size(); ++i) {
            cout << vt[i].id << " " << vt[i].tot << endl;
        }
    }
    return 0;
}
