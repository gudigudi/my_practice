#include <stdio.h>

typedef struct _ds1 {
    unsigned int id;
    char * name;
    unsigned int type;
} ds1;

int main(void)
{
    ds1 d1, d2;
    d1.id = 12;
    d1.name = "gudi";
    d1.type = 3;

    printf("1\nid:\t%d\tname:\t%s\ttype:\t%d\n", d1.id, d1.name, d1.type);

    return 0;
}
