#define INIT_SUCCESS 0

#include <pthread.h>

pthread_mutex_t initmtx = PTHREAD_MUTEX_INITIALIZER;

void proginit(int argc, char *argv[])
{
    static volatile int initialised = 0;

    // only run once
    pthread_mutex_lock(&initmtx);
    if (initialised) {
        pthread_mutex_unlock(&initmtx);
        return ;
    }

    // critical region begins
    if (!initialised) {
        // initialise program state
        initialised = 1;
    }
    // critical region ends
    pthread_mutex_unlock(&initmtx);

    return ;
}

