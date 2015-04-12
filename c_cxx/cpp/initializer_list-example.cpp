#include <iostream>
#include <initializer_list>

int main(void)
{
  std::initializer_list<int> empty_list;
  std::cout << "empty_list.size(): " << empty_list.size() << '\n';

  std::initializer_list<int> digits{1, 2, 3, 4, 5};
  std::cout << "digits.size(): " << digits.size() << '\n';

  auto fractions = {3.14159, 2.71828};
  std::cout << "fractions.size(): " << fractions.size() << '\n';

  return 0;
}
