#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

void sighandler(int);

int main(void)
{
    signal(SIGINT, sighandler);

    while(1) {
        printf("sleep 1 second...\n");
        sleep(1);
    }

    return 0;
}

void sighandler(int signum)
{
    printf("get signal %d, jump...\n", signum);
    exit(1);
}
