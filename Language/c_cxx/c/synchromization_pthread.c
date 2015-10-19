#include <stdio.h>
#include <pthread.h>

pthread_mutex_t mutex;
pthread_cond_t cond;

void * test(void * arg)
{
    pthread_mutex_lock(&mutex);

    for (int i = 0; i < 10; ++i) {
        printf("T1 : %d\n", i);
        sleep(1);

        if (i == 5)  pthread_cond_wait(&cond, &mutex);
    }

    pthread_mutex_unlock(&mutex);
    return (void*)0;
}

void * test2(void * arg)
{
    pthread_mutex_lock(&mutex);
    pthread_mutex_lock(&mutex);

    for (int i = 0; i < 10; ++i) {
        printf("T2 : %d\n", i);
        sleep(1);
    }

    pthread_cond_signal(&cond);

    pthread_mutex_unlock(&mutex);
    pthread_mutex_unlock(&mutex);
    return (void *)0;
}

int main(int argc, char *argv[])
{
    pthread_attr_t p_attr;
    pthread_attr_init(&p_attr);
    pthread_attr_setdetachstate(&p_attr, PTHREAD_CREATE_DETACHED);

    pthread_mutexattr_t m_attr;
    pthread_mutexattr_init(&m_attr);
    pthread_mutexattr_settype(&m_attr, PTHREAD_MUTEX_RECURSIVE);

    pthread_mutex_init(&mutex, &m_attr);
    pthread_cond_init(&cond, NULL);

    pthread_t tid1, tid2;
    pthread_create(&tid1, &p_attr, test, NULL);
    sleep(1);
    pthread_create(&tid2, &p_attr, test2, NULL);

    pthread_attr_destory(&p_attr);
    pthread_mutexattr_destory(&m_attr);

    sleep(30);

    return 0;
}
