#include <iostream>
#include <string>

struct Sale_data
{
    std::string bookNo;
    unsigned units_sold = 0;
    double revenus = 0.0;
};

int main()
{
    Sale_data book;
    double price;
    std::cin >> book.bookNo >> book.units_sold >> price;
    book.revenus = book.units_sold * price;
    std::cout << book.bookNo << " " << book.units_sold << " " << price;

    return 0;
}
