#include <stdio.h>
#include <stdlib.h>

#define ARSIZE 10

int main(void)
{
    int ch_arr[ARSIZE], count1;
    int count2, stop, lastchar;

    lastchar = 0;
    stop = 0;
    /*
     * Read character into array.
     * Stop if end of line, or array full.
     */
    while(stop != 1) {
        ch_arr[lastchar] = getchar();
        if (ch_arr[lastchar] == '\n') {
            stop = 1;
        } else {
            lastchar++;
        }
        if (lastchar == ARSIZE) {
            stop = 1;
        }
    }
    lastchar--;

    /*
     * Now the traditional bubble sort.
     */
    count1 = 0;
    while(count1 < lastchar) {
        count2 = count1 + 1;
        while(count2 <= lastchar) {
            if (ch_arr[count1] > ch_arr[count2]) {
                // swap
                int temp;
                temp = ch_arr[count1];
                ch_arr[count1] = ch_arr[count2];
                ch_arr[count2] = temp;
            }
            count2++;
        }
        count1++;
    }

    count1 = 0;
    while(count1 <= lastchar) {
        printf("%c\n", ch_arr[count1]);
        count1++;
    }
    return 0;
}
