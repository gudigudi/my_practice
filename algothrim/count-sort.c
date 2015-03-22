#include <stdlib.h>

// paixu ar zhongde n geyuansu, fanweishi [0, k)
int countingSort(int *ar, int n, int k)
{
    int i, idx = 0;
    int *B = calloc(k, sizeof(int));

    for (i = 0; i < n; ++i) {
        B[ar[i]]++;
    }

    for (i = 0; i < k; ++i) {
        while(B[i]-- > 0) {
            ar[idx++] = i;
        }
    }

    free(B);
}
