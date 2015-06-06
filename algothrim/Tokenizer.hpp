#include <fstream>
#include <vector>
#include <stack>
#include <stdlib.h>

using namespace std;

class Tokenizer
{
public:
    Tokenizer(istream & input) : currentLine(1), errors(0), inputStream(input) {}

    char getNextOpenClose();
    string getNextID();
    int getLineNumber() const;
    int getErrorCount() const;
private:
    enum CommentType { SLASH_SLASH, SLASH_STAR };

    istream & inputStream;
    char ch;
    int currentLine;
    int errors;

    bool nextChar();
    void putBackChar();
    void skipComment(CommentType start);
    void skipQuote(char quoteType);
    string getRemainingString();
};
