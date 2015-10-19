// Puzzle class interface: solve word search puzzle.
//
// CONSTRUCTION: with no initializer.
// ****************** PUBLIC ***********************
// int SolvePuzzle()  --> Print all words found in the puzzle;
//                        return number of matches

#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include "matrix.h"

using namespace std;

class Puzzle
{
public:
    Puzzle();
    int solvePuzzle() const;

private:
    matrix<char> theBoard;
    vector<string> theWords;
    ifstream puzzleStream;
    ifstream wordStream;

    void openFile(const string & message, ifstream & inFile);
    void readPuzzle();
    void readWords();
    int solveDirection(int baseRow, int baseCol, int rowDelta, int colDelta) const;
}
