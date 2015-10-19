#include <iostream>

int main(void)
{
  int val_small = 0, val_big = 0;
  std::cout << "please input two integers:";
  std::cin >> val_small >>val_big;

  if (val_small > val_big)
  {
    int tmp = val_small;
    val_small = val_big;
    val_big = tmp;
  }

  for (int i = 0; i <= val_big; ++i)
  {
    std::cout << i << std::endl;
  }

  return 0;
}
