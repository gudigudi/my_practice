#include <stdio.h>

#define LENGTH 10
#define WIDTH  5
#define NEWLINE '\n'

int main(void)
{
    int area;

    area = LENGTH * WIDTH;
    printf("value of area : %d\n", area);
    printf("%c\n", NEWLINE);

    return 0;
}
