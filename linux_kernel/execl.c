#include <stdio.h>
#include <unistd.h>

int main(void)
{
    printf("Executing ls\n");
    execl("/bin/ls", "ls", "-l", NULL);

    // if execl return, it's calling-fail!
    perror("execl failed to run ls");
    return 0;
}
