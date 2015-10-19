#include <stdio.h>

int main(void)
{
    int i, j, p, t, k, temp, n = 10, max;
    int a[10] = {99, 45, 9, 75, 42, 28, 66, 70, 17};
    int b[10], d[10];
    int c[10];

    for (i = 0; i < n; ++i) {
        c[i] = 0;
        b[i] = a[i];
    }

    t = n;

    while(t) {
        i = p = t;
        k = 1;
        for (i = 0; i < t; ++i) {
            d[i] = i;
        }
        while(i) {
            i = i/2;
            k = 2*k;
        }
        k = k/2;
        while(k) {
            for (i = 0, j = p - 1; i < k; ++i, j--) {
                if (b[i] > b[j]) {
                    c[i] = d[i];
                } else {
                    c[i] = d[j];
                }
            }
            for (j = 0; j < k; ++j) {
                i = c[j];
                b[j] = a[i];
                d[j] = c[j];
            }
            k = k/2;
            p = (p + 1) / 2;
        }
        max = c[0];
        if (a[max] > a[t - 1]) {
            temp = a[t - 1];
            a[max] = a[t - 1];
            a[t - 1] = temp;
        }
        for (i = 0; i < n; ++i) {
            b[i] = a[i];
        }
        t--;
        for (i = 0; i < n; ++i) {
            printf("%d\n", a[i]);
        }
    }

    return 0;
}
