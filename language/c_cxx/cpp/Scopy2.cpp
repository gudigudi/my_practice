//: C02: Scopy.cpp
// Copy one file to another, a line at a time
#include <string>
#include <fstream>
using namespace std;

int main(void)
{
  ifstream in("Scopy.cpp");
  ofstream out("Scopy2.cpp");
  string s;
  while(getline(in, s)) {
    out << s << "\n";
  }
  return 0;
}
