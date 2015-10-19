#include <errno.h>
#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int years;

    printf("Enter your age in years : ");
    fflush(stdout);
    errno = 0;
    if(scanf("%d", &years) != 1 || errno)
        return EXIT_FAILURE;

    printf("Your age in days is %d\n", years * 365);
    return 0;
}
