//: C02: HelloStrings.cpp
// The basics of the standard C++ string class
#include <string>
#include <iostream>
using namespace std;

int main(void)
{
  string s1, s2;
  string s3 = "Hello, world.";
  string s4("I am");
  s2 = "Today";
  s1 = s3 + " " + s4;
  s1 += " 8 ";
  cout << s1 + s2 + "!" << endl;

  return 0;
}
