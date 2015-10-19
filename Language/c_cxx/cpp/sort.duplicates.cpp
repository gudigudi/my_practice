#include <vector>

// Return true if a has duplicates; false otherwise.
template <class Comparable>
bool duplicates( const vector<Comparable> & a )
{
    const int n = a.size();

    for ( int i = 0; i < n; i++ ) {
        for ( int j = i + 1; j < n; j++ ) {
            if ( a[i] == a[j] )
                return true;
        }
    }

    return false;
}
