#include <iostream>

using namespace std;

struct Student
{
    string firstName;
    string lastName;
    int studentNum;
    double gradePointArg;
};

// Print the student information.
void printInfo( const Student & s )
{
    cout << "ID is " << s.studentNum << endl;
    cout << "Name is " << s.firstName << " " << s.lastName << endl;
    cout << "GPA is " << s.gradePointArg << endl;
}

// Simple main.
int main()
{
    Student mary;

    mary.lastName = "Smith";
    mary.firstName = "Mary";
    mary.gradePointArg = 4.0;
    mary.studentNum = 123456789;

    printInfo( mary );

    return 0;
}

