int search(void *t, int(*cmp)(const void *, const void *))
{
    int i;
    for (i = 0; i < n; ++i) {
        if (!cmp(ar[i], t)) {
            return i;
        }
    }
    return 0;
}
