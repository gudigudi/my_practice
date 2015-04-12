#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(void)
{
    time_t t = time(NULL);
    struct tm *tmptr = localtime(&t);
    printf("Today is        %s", asctime(tmptr));
    tmptr->tm_mon -= 100;
    time_t result = mktime(tmptr);
    if (result == ((time_t)-1)) {
        fprintf(stderr, "mktime() failed in file %s at line # %d\n",
                __FILE__, __LINE__-3);
        exit(EXIT_FAILURE);
    }
    printf("100 months ago was %s\n", asctime(tmptr));

    return 0;
}
