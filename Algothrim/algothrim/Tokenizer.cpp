#include "Tokenizer.hpp"
#include <iostream>

using namespace std;

struct Symbol {
    char token;
    int theLine;
};

class Balance
{
public:
    Balance(istream & input) : tok(input), errors(0) {}

    int checkBalance();
private:
    Tokenizer tok;
    int errors;

    void checkMatch(const Symbol & opSym, const Symbol & clSym);
};
