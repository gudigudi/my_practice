#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int ch;

    ch = getchar();
    while(ch != 'a') {
        if (ch != '\n') {
            printf("ch was %c, value %d\n", ch, ch);
        }
        ch = getchar();
    }
    return 0;
}
