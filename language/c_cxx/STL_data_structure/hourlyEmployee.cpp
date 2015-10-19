
#include <iostream>
#include "HourlyEmployee.hpp"

HourlyEmployee::HourlyEmployee() {}
void HourlyEmployee::readInto()
{
    const string NAME_HOURS_RATE_PROMPT =
        "Please enter a name, hours worked and pay rate. The sentinels are ";

    cout << NAME_HOURS_RATE_PROMPT << NAME_SENTINEL << " "
        << HOURS_WORKED_SENTINEL << " " << PAY_RATE_SENTINEL << ": ";

    cin >> name >> houreWorked >> payRate;

    grossPay = houreWorked * payRate;
}
bool HourlyEmployee::isSentinel() const
{
    if (name == NAME_SENTINEL &&
        hoursWorked == HOURS_WORKED_SENTINEL &&
        payRate == PAY_RATE_SENTINEL)
        return true;
    return false;
}

const int HourlyEmployee::HOURS_WORKED_SENTINEL = -1;
const double HourlyEmployee::PAY_RATE_SENTINEL = -1.00;
