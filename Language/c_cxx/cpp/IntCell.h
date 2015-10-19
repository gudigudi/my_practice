#ifndef _IntCell_H_
#define _IntCell_H_

// A class for simulating an integer memory cell.

class IntCell
{
    public:
        explicit IntCell( int initivalValue = 0 );
        int read() const;
        void write( int x );
    private:
        int storedValue;
};

#endif
