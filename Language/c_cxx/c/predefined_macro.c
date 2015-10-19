/*
 * show information about predefined macro in C
 */
#include <stdio.h>

int main(void)
{
    printf("the line is %d\n", __LINE__);
    printf("the file is %s\n", __FILE__);
    printf("the date is %s\n", __DATE__);
    printf("the time is %s\n", __TIME__);
    printf("the stdc is %d\n", __STDC__);
    // some C compiler don't define the macro
#ifdef __STDC__VERSION__
    printf("the stdc version is %s\n", __STDC__VERSION__);
#endif
#ifdef __STDC__HOSTED__
    printf("the stdc hosted is %s\n", __STDC__HOSTED__);
#endif
#ifdef __STDC_IEC_559__
    printf("the stdc iec559 standard is %d\n", __STDC_IEC_559__);
#endif
#ifdef __STDC_IEC_559__COMPLEX__
    printf("the stdc iec559 standard is %d\n", __STDC_IEC_559__COMPLEX__);
#endif
#ifdef __STDC_IEO10646__
    printf("the stdc ieo10646 standard is %ld\n", __STDC_IEO10646__);
#endif


    return 0;
}
