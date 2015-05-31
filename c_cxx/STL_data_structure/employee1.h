#ifndef EMPLOYEE
#define EMPLOYEE

#include <string>

using namespace std;

class Employee
{
public:
    Employee ();
    virtual ~Employee ();

    void readInto();
    bool isSentinel() const;
    void printOut() const;
    void getCopyOf(const Employee& otherEmployee);
    bool makesMoreThan(const Employee& otherEmployee) const;
protected:
    string name;
    double grossPay;

    const static string EMPTY_STRING;
    const static string NAME_SENTINEL;
    const static double GROSS_PAY_SENTINEL;
};
#endif
