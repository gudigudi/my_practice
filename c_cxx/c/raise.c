#include <signal.h>
#include <stdio.h>

void signal_catchfunc(int);

int main(void)
{
    int ret;

    ret = signal(SIGINT, signal_catchfunc);

    if (ret == SIG_ERR) {
        printf("Error : cannot set signal program\n");
        exit(0);
    }
    printf("start set a signal\n");
    ret = raise(SIGINT);
    if (ret != 0) {
        rp
    }
    return 0;
}
