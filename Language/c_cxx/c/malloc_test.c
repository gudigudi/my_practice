#include <stdio.h>
#include <string.h>

void f(char *inner)
{
    char temp[11];
    strcpy(temp, "algorithms");
    int i;

    for (i = 0; i < 11; ++i) {
        inner[i] = temp[i];
    }
}

int main(int argc, char *argv[])
{
    char *ar1 = malloc(132);
    char *ar2 = malloc(132);
    int i = 17, j;

    f(ar2);

    return 0;
}
