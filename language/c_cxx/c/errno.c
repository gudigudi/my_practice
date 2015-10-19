#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>

extern int errno;

int main(void)
{
    /* pointer to char, requesting dynamic allocation of 2,000,000,000
     * storage elements (declared as an integer constant of type
     * unsigned long int). (If your system has less than 2GB of memory
     * available, then this call to malloc will fail)
     */
    char *ptr = malloc(2000000000UL);

    if (ptr == NULL) {
        puts("malloc failed");
        puts(strerror(errno));
    }
    else {
        /* the rest of the code hereafter can assume that 2,000,000,000
         * chars were successfully allocated ...
         */
        free(ptr);
    }

    exit(EXIT_SUCCESS);
}
