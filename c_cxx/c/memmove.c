#include <stdio.h>
#include <string.h>

int main(void)
{
    char str[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    puts(str);
    memmove(str+6, str+3, 7);
    puts(str);

    return 0;
}
