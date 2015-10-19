#include <stdlib.h>
#include <stdio.h>

/* _Exit does not call functionx registered with atexit. */
void f1()
{
    puts("pushed first");
}

void f2()
{
    puts("pushed second");
}

int main()
{
    printf("Enter main()\n");
    atexit(f1);
    atexit(f2);
    fflush(stdout);    /* _Exit does not flush unwritten buffered data */

    _Exit(0);
}
