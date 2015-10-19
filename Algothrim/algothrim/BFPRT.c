/*
 * algorithm BFPRT
 */

#define SWAP(a, p1, p2, type) { \
    type _tmp__ = a[p1];        \
    a[p1] = a[p2];              \
    a[p2] = _tmp__;             \
}

/*
 * 寻找在数组ar[left], ar[left+gap], ar[left+gap*2], ar[left+gap*3]的
 * 4个元素的中值, 在结束的时候确保ar[left+gap*2]包含有中值.
 */
static void medianOfFour(void **ar, int left, int gap,
                         int(*cmp)(const void *, const void *)) {
    int pos1 = left, pos2, pos3, pos4;
    void *a1 = ar[pos1];
    void *a2 = ar[pos2=pos1+gap];
    void *a3 = ar[pos3=pos2+gap];
    void *a4 = ar[pos4=pos3+gap];

    if (cmp(a1, a2) <= 0) {
        if (cmp(a2, a3) <= 0) {
            if (cmp(a3, a4) <= 0) {
                SWAP(ar, pos3, pos4, void *);
            }
        } else {
            if (cmp()) {

            }
        }
    }
}
