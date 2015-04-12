#define N 10                                       /* 缓冲区中的槽数目 */
int count = 0;                                     /* 缓冲区中的数据项数目 */

void producer(void)
{
    int item;

    while(TRUE) {                                  /* 无限循环 */
        item = producer_item();                    /* 产生下一个新数据项 */
        if (count == N) sleep();                   /* 如果缓冲区满了，就 */
        insert_item(item);
        count++;
        if (count == 1) wakeup(consumer);
    }
}

void consumer(void)
{
    int item;

    while(TRUE) {
        if (count == 0) sleep();
        item = remove_item();
        count--;
        if (count == N - 1) wakeup(producer);
        consumer_item(item);
    }
}
