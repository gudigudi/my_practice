#include <linux/stddef.h>
#include <stdio.h>

int main(void)
{
    FILE *fp;

    fp = fopen("file.txt", "r");
    if (fp != NULL) {
        printf("success to open the file file.txt\n");
        fclose(fp);
    }

    return 0;
}
