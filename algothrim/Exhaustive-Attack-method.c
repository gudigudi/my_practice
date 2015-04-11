/*将 A、 B、 C、 D、 E、 F 这六个变量排成如图所示的三角形,
 * 这六个变量分别取[1,6]上的整数,且均不相同。
 * 求使三角形三条边上的变量之和相等的全部解
*/
#include <stdio.h>

int main(void)
{
    int a, b, c, d, e, f;
    for (a = 1; a <= 6; ++a) {
        for (b = 1; b <= 6; ++b) {
            if (b == a)    continue;
            for (c = 1; c <= 6; ++c) {
                if (c == a || c == b)    continue;
                for (d = 1; d <= 6; ++d) {
                    if (d == a || d == b || d == c)    continue;
                    for (e = 1; e <= 6; ++e) {
                        if (e == a || e == b || e == c || e == d)    continue;
                        f = 21 - (a+b+c+d+e);
                        if((a+b+c == c+d+e) && (a+b+c == e+f+a)) {
                            printf("%6d\n", a);
                            printf("%4d%4d\n", b, f);
                            printf("%2d%4d%4d\n", c, d, e);

                        }
                    }
                }
            }
        }
    }
    return 0;
}
