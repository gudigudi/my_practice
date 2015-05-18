#include <stdio.h>

int main(void)
{
    int tab[8][4][2];
    int ndx = 0;

    for (int i = 0; i < 8; ++i) {
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < 2; ++k) {
                tab[i][j][k] = ndx++;
            }
        }
    }
    return 0;
}
