/*
 * goroutine vs C thread
 * test C thread running time
 */

#include <stdio.h>
#include <pthread.h>

void forfunc(int fornum) {
    int sum = 0;
    for (int i = 0; i < fornum; ++i) {
        sum += i;
    }
}

int main(void)
{

    pthread_t thread_num = 1024;
    pthread_create(thread_num, forfunc, NULL);

    return 0;
}
