#include <stdlib.h>

extern int hash(void *elt);
extern int numBucket(int numElements);

// 桶中的元素链表
typedef struct entry {
    void         *element;
    struct entry *next;
} ENTRY;

// 维护每个桶中元素的数目，并且指向第一个元素
typedef struct {
    int   size;
    ENTRY *head;
} BUCKET;

// 桶的指针，以及桶的数量
static BUCKET *bucket = 0;
static int num = 0;

// 一个接一个移除，并且覆盖ar
void extract(BUCKET *buckets, int(*cmp)(const void *, const void *),
             void **ar, int n)
{
    int i, low;
    int idx = 0;
    for (i = 0; i < num; ++i) {
        ENTRY *ptr, *tmp;
        if (buckets[i].size == 0) continue;  // 空桶

        ptr = buckets[i].head;
        if (buckets[i].size == 1) {
            ar[idx++] = ptr->element;
            free(ptr);
            buckets[i].size = 0;
            continue;
        }

        // 对链表中的元素执行插入排序，然后插入到数组中，然后释放链表
        low = idx;
        ar[idx++] = ptr->element;
        tmp = ptr;
        ptr = ptr->next;
        free(tmp);
        while(ptr != NULL) {
            int i = idx - 1;
            while(i >= low && cmp(ar[i], ptr->element) > 0) {
                ar[i+1] = ar[i];
                i--;
            }
            ar[i+1] = ptr->element;
            tmp = ptr;
            ptr = ptr->next;
            free(tmp);
            idx++;
        }
        buckets[i].size = 0;
    }
}

void sortPointers(void **ar, int n,
                  int(*cmp)(const void *, const void *))
{
    int i;
    num = numBuckets(n);
    buckets = (BUCKET *) calloc(num, sizeof(BUCKET));
    for (i = 0; i < n; ++i) {
        int k = hash(ar[i]);
        // 插入每个元素并且增加计数
        ENTRY *e = (ENTRY *) calloc(1, sizeof(ENTRY));
        e->element = ar[i];
        if (buckets[k].head == NULL) {
            buckets[k].head = e;
        } else {
            e->next = buckets[k].head;
            buckets[k].head = e;
        }

        buckets[k].size++;
    }

    // 读出并且覆盖ar
    extract (buckets, cmp, ar, n);

    free(buckets);
}

static int num;

// 使用的桶数量和元素数量一样
int numBuckets(int numElements)
{
    num = numElements;
    return numElements;
}

// 散列函数将元素映射到桶中，
