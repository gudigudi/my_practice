#include <stdio.h>
#include <stdlib.h>

int main(void)
{
    const char* file_name = "file.txt";

    FILE *fp = fopen(file_name, "w");
    if (fp == NULL) {
        perror("fopen()");
        fprintf(stderr, "fopen() failed in file %s at # %d",
                __FILE__, __LINE__-4);
        exit(EXIT_FAILURE);
    }

    // Normal processing continues here.
    fclose(fp);

    system("ls");

    // Remove an existing file.
    int ret_code = remove(file_name);
    if (ret_code != 0) {
        perror("remove()");
        fprintf(stderr, "remove() failed in file %s at line # %d\n",
                __FILE__, __LINE__-4);
        exit(EXIT_FAILURE);
    }

    system("ls");

    // Try removing a nonexisting file.
    ret_code = remove(file_name);
    if (ret_code != 0) {
        perror("remove()");
        fprintf(stderr, "remove() failed in file %s at line # %d\n",
                __FILE__, __LINE__-4);
        exit(EXIT_FAILURE);
    }

    return 0;
}
