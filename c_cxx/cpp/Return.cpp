#include <iostream>
using namespace std;

char cfunc(int i)
{
  if (i == 0)  return 'a';
  if (i == 1)  return 'g';
  if (i == 5)  return 'c';
  return 'b';
}

int main(void)
{
  cout << "type an integer: ";
  int val;
  cin >> val;
  cout << cfunc(val) << endl;
  return 0;
}
