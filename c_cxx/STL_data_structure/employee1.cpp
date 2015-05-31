
#include <iostream>
#include <iomanip>
#include "employee1.h"

Employee::Employee()
{
    name = EMPTY_STRING;
    grossPay = 0.0;
}

void Employee::readInto()
{
    const string NAME_AND_PAY_PROMPT =
        "Please enter a name and gross pay, to quit, enter";
    cout << NAME_AND_PAY_PROMPT << NAME_SENTINEL << " "
        << GROSS_PAY_SENTINEL;
    cin >> name >> grossPay;
}

bool Employee::isSentinel() const
{
    if (name == NAME_SENTINEL && grossPay == GROSS_PAY_SENTINEL)
        return true;
    return false;
}

void Employee::printOut() const {
    cout << name << "$" << setiosflags(ios::fixed) << setprecision(2)
        << grossPay << endl;
}

void Employee::getCopyOf(const Employee& otherEmployee)
{
    name = otherEmployee.name;
    grossPay = otherEmployee.grossPay;
}

bool Employee::makesMoreThan(const Employee& otherEmployee) const
{
    return grossPay > otherEmployee.grossPay;
}
