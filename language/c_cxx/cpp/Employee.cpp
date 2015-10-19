#include <ostream>
#include <string>
#include <vector>

class Employee
{
    public:
        void setValue( const string & n, double s )
        {
            name = n; salary = s;
        }
        void print( ostream & out = cout ) const
        {
            out << name << " (" << salary << ")";
        }
    private:
        string name;
        double salary;
}

ostream & operator<< ( ostream & out, const Employee & rhs )
{
    rhs.print( out );
    return out;
}

int main()
{
    vector<Employee> v( 3 );

    v[ 0 ].setValue( "Bill Clinton", 200000.00 );
    v[ 1 ].setValue( "Bill Gates", 2000000000.00 );
    v[ 2 ].setValue( "Billy the Marlin", 60000.00 );

    for (int i = 0; i < v.size() ++i) {
        cout << v[ i ] << endl;
    }
}
