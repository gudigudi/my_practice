#include <stdio.h>

/*
 * @brief 将塔座x上按直径由小到大且自上而下编号
 * 为1至n的n个圆盘按规则搬到塔座z上，y可用做辅助塔座.
 * @param[in] n 圆盘个数
 * @param[in] x 源塔座
 * @param[in] y 辅助塔座
 * @param[in] z 目标塔座
 * @return 无
 */
void hanoi(int n, char x, char y, char z)
{
    if (n == 1) {
        // 将编号为n的圆盘从x移到z
        printf("%d from %c to %c\n", n, x, z);
        return ;
    } else {
        // 将x上编号1至n-1的圆盘移到y，z作辅助塔
        hanoi(n-1, x, z, y);
        // 将编号为n的圆盘从x移到z
        printf("%d from %c to %c\n", n, x, z);
        // 将y上编号1至n-1的圆盘移到z，x作辅助塔
        hanoi(n-1, y, x, z);
    }
}

int main(void)
{
    int n;
    scanf("%d", &n);
    printf("%d\n", (1 << n) - 1);  // 总次数
    hanoi(n, 'A', 'B', 'C');
    return 0;
}
