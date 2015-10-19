#include <stdio.h>

const char* format = "Hello, %s!\n";
char* name = "gudi";

int main(int argc, char *argv[])
{
    printf(format, name);
    return 0;
}
