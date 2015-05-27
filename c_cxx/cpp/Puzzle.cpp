

// Constructor for Puzzle class.
// Prompts for and reads puzzle and dictionary files.
Puzzle::Puzzle() : theBoard(0, 0)
{
    openFile("Enter puzzle file", puzzleStream);
    openFile("Enter dictionary name", wordStream);
    readPuzzle();
    readWords();
}

// Print a prompt and open a file.
// Retry until open is successful.
void Puzzle::openFile(const string & mesg, ifstream & inFile)
{
    string name;

    do {
        inFile.clear();
        cout << mesg << ": ";
        cin >> name;
        inFile.open(name.c_str());
    } while(!inFile);
}

// Routine to read the dictionary.
// Error message is printed if dictionary is not sorted.
void puzzle::readWords()
{
    string thisWord;
    int numEntries = 0;

    for ( ; wordStream >> thisWord; numEntries++) {
        theWords.push_back(thisWord);

        if (numEntries != 0 && theWords[numEntries] < theWords[numEntries - 1]) {
            cout << "Dictionary is not sorted... skipping" << endl;
            continue;
        }
    }
}

// Return a vector<char> containing characters in str.
vector<char> toVec(const string & str)
{

}
