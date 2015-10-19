#include <time.h>
#include <stdio.h>

int main(void)
{
    time_t result = time(NULL);
    printf("%s\n", ctime(&result));

    return 0;
}
