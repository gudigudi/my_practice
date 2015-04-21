/** @file queue.c
 * @brief 队列，顺序存储，循环队列.
 */
#include <stdlib.h>  // for malloc(), free
#include <string.h>  // for memcpy()

typedef int queue_elem_t;  // 元素的类型

/*
 * @struct
 * @brief 队列的结构体定义
 * @note 无
 */
typedef struct _queue_t {
    int front;       // 对头
    int rear;        // 队尾
    int capacity;    // 容量大小，以元素为单位
    queue_elem_t *elems;  // 存放数据的内存块
} queue_t;

/**
 * @brief 创建队列.
 * @param[in] capacity初始容量
 * @return 队列
 */
queue_t* queue_create(int capacity)
{
    queue_t *q = (queue_t*)malloc(sizeof(queue_t));
    q->front = 0;
    q->rear = 0;
    q->capacity = capacity;
    q->elems = (queue_elem_t*)malloc(capacity * sizeof(queue_elem_t));
    return q;
}

/**
 * @brief 销毁队列.
 * @param[in] q 队列对象的指针
 * @return 无
 */
void queue_destory(queue_t *q)
{
    free(q->elems);
    free(q);
}

/**
 * @brief 判断队列是否为空.
 * @param[in] q 队列结构体的指针
 * @return 是空，返回TRUE， 否则返回 FALSE
 */
bool queue_empty(const queue_t *q)
{
    return q->front == q->rear;
}

/**
 * @brief 获取元素个数.
 * @param[in] s 栈对象的指针
 * @return 元素个数
 */
int queue_size(const queue_t *q)
{
    return (q->rear - q->front + q->capacity) % q->capacity;
}

/**
 * @brief 在队尾添加元素.
 * @param[in] q 指向队列结构体的指针
 * @param[in] x 要添加的元素
 * @return 无
 */
void queue_push(queue_t *q, const queue_elem_t x)
{
    if ((q->rear+1) % q->capacity == q->front) {

    }
}
