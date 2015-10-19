#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    int dividend = 50;
    int divisor = 0;
    int quotient;

    if (divisor == 0) {
        /* Example handling of this error, Writing a message to stderr, and
         * exiting with failure.
         */
        fprintf(stderr, "Division by zero! Aborting...\n");
        exit(EXIT_FAILURE);
    }

    quotient = dividend / divisor;
    return 0;
}
