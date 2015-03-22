/* test enum in C
 *
 *
 * */
#include <stdio.h>

int main(void)
{
    enum color {
        black,
        red = 5,
        green,
        yellow
    };

    enum color b = black;
    printf("black = %d\n", b);

    enum color r = red;
    printf("red = %d\n", r);

    enum color g = green;
    printf("green = %d\n", g);

    enum color y = yellow;
    printf("yellow = %d\n", y);

    return 0;
}
