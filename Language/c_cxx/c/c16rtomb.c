#include <stdio.h>
#include <locale.h>
#include <uchar.h>
#include <stdlib.h>

mbstate_t state;

int main(void)
{
    setlocale(LC_ALL, "en_US.utf8");
    char16_t str[] = u"zß水";  // or z\u00df\u6c34
    size_t str_sz = sizeof str / sizeof *str;

    printf("Processing %zu UCS-2 code units: [ ", str_sz);
    for ( size_t n = 0; n < str_sz; ++n) printf("%#x ", str[n]); puts("]");

    char out[MB_CUR_MAX];
    for ( size_t n = 0; n < str_sz; ++n) {
        int rc = c16rtomb(out, str[n], &state);
        printf("%#x converted to [ ", str[n]);
        for ( int x = 0; x < rc; ++x) printf("%#x ", +(unsigned char)out[x]); puts("]");
    }

    return 0;
}
