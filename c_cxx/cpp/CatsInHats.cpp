#include <iostream>
using namespace std;

void removeHat(char cat)
{
  for (char c = 'A'; c < cat; c++)
    cout << "  ";
  if (cat <= 'Z') {
    cout << "cat " << cat << endl;
    removeHat(cat + 1);
  } else {
    cout << "VOOM!!!" << endl;
  }
}

int main(void)
{
  removeHat('A');
  return 0;
}
