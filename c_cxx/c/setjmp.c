#include <stdio.h>
#include <stdlib.h>
#include <setjmp.h>

void jmpfunction(jmp_buf env_buffer);

int main(void)
{
    int val;
    jmp_buf env_buffer;

    // set longjmp
    val = setjmp(env_buffer);
    if (val != 0) {
        printf("longjmp's return value = %d\n", val);
        exit(0);
    }
    printf("jmp calling\n");
    jmpfunction(env_buffer);

    return 0;
}

void jmpfunction(jmp_buf env_buffer)
{
    longjmp(env_buffer, "w3cschool.cc");
}
