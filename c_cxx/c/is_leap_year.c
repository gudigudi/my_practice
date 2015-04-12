#include <stdio.h>

int is_leap_year(int year)
{
    if ((year % 400) == 0) {
        return 1;
    } else if (((year % 4) == 0) &&
               ((year % 100) != 0)){
        return 1;
    }
    return 0;
}

int main(void)
{
    int y[3] = {4, 100, 2000};
    for (int i = 0; i < 3; ++i) {
        if (is_leap_year(y[i])) {
            printf("year %d is leap year\n", y[i]);
        } else {
            printf("year %d is not leap year\n", y[i]);
        }
    }
    return 0;
}
