#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    // Allocate arrayA with 6 integers.
    int* pa = realloc(NULL, 6 * sizeof(int));
    if (!pa) {    // if realloc() fails, terminate program
        printf("realloc() failed in file %s at line %d\n",
                __FILE__, __LINE__);
        printf("***  PROGRAM TERMINATED  *** \n");
        exit(1);
    }
    printf("starting address of pa:   %p\n", (void*)pa);

    // Allocate a single integer to provide a barrier to an expansion of pa.
    int* pb = malloc(1 * sizeof(int));
    if (!pb) {    // if malloc() fails, terminate program
        printf("malloc() failed in file %s at line %d\n",
                __FILE__, __LINE__);
        printf("***  PROGRAM TERMINATED  *** \n");
        exit(1);

    }
    return 0;
}
