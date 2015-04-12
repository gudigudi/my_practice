#include <iostream>
using namespace std;

void f(int a)
{
  cout << "a = " << a << endl;
  a = 5;
  cout << "a = " << a << endl;
}

int main(void)
{
  int x = 47;
  cout << "x = " << x << endl;
  f(x);
  cout << "x = " << x << endl;

  return 0;
}
