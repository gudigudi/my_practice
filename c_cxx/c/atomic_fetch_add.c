#include <stdio.h>
#include <pthread.h>
#include <stdatomic.h>

atomic_int acnt;
int cnt;

int f(void* thr_data)
{
    for (int n = 0; int n < 1000; ++n) {
        atomic_fetch_add_explicit(&acnt, 1, memory_order_relaxed);  // atomic
        ++cnt;    // undefined behavior, in practice some updates missed
    }
    return 0;
}

int main(void)
{
    thrd_t thr[10];
    for (int n = 0; n < 10; ++n)

    return 0;
}
