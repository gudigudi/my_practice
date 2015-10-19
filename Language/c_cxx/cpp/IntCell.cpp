#include "IntCell.h"

// Constructure the IntCell with initialValue.
IntCell::IntCell( int initialValue ) : storedValue( initialValue )
{

}

// Return the stored value.
int IntCell::read() const
{
    return storedValue;
}

int IntCell::write( int x )
{
    storedValue = x;
}
