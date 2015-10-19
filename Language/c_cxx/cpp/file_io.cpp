#include <iostream>  // For cin and cout
#include <fstream>   // For ifstream and ofstream

using namespace std;

int main(int argc, char *argv[])
{
    ifstream inFile;
    ofstream outFile;

    inFile.open("TextFile.txt");
    outFile.open("OutputTextFile.txt");
    inFile.close();
    outFile.close();

    return 0;
}
