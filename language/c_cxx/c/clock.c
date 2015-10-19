#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <pthread.h>    // pthread.h in POSIX

// the function f() does some time-consuming work
int f(void* thr_data)
{
    volatile double d = 0;
    for (int i = 0; i < 10000; ++i) {
        for (int j = 0; j < 10000; ++j) {
            d += d * i * j;
        }
    }
    return 0;
}

int main(void)
{
    struct timespec ts1, tw1;        // both C11 and POSIX
    clock_gettime(CLOCK_PRECESS_CPUTIME_ID, &ts1);    // POSIX
    clock_gettime(CLOCK_MONOTONIC, &tw1);    // POSIX; use timespec_get in C11
    clock_t t1 = clock();

    pthread_t thr1, thr2;    // C11; use pthread_t in POSIX
    pthread_create(&thr1, f, NULL);    // C11; use pthread_create in POSIX
    pthread_create(&thr2, f, NULL);
    pthread_join(thr1, NULL);    // C11; use pthread_join in POSIX
    pthread_join(thr2, NULL);

    struct timespec ts2, tw2;
    clock_gettime(CLOCK_PRECESS_CPUTIME_ID, &ts2);
    clock_gettime(CLOCK_MONOTONIC, &tw2);
    clock_t t2 = clock();
    return 0;
}
