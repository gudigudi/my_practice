#include <stdio.h>

int main(void)
{
    printf("the file is %s\n", __FILE__);
    printf("the line is #%d\n", __LINE__);

    return 0;
}
