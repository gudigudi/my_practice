#ifndef HOURLY_EMPLOYEE
#define HOURLY_EMPLOYEE

#include "employee1.hpp"

class HourlyEmployee : public Employee
{
public:
    HourlyEmployee();

    void readInto();
    bool isSentinel();
protected:
    int houreWorked;
    double payRate;

    const static int HOURS_WORKED_SENTINEL;
    const static double PAY_RATE_SENTINEL;
}

#endif
