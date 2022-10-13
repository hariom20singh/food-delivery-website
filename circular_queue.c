#include <stdio.h>
#include <stdlib.h>

struct CircularQueue
{
    int size;
    int f;
    int r;
    int *arr;
};

int IsFull(struct CircularQueue *q)
{
    if ((q->r+1)%q->size == q->f)
    {
        return 1;
    }
    return 0;
}
int IsEmpty(struct CircularQueue *q)
{
    if (q->f == q->r)
    {
        return 1;
    }
    return 0;
}

// enqueue operation
void enqueue(struct CircularQueue *q, int val)
{
    if (IsFull(q))
    {
        printf("Queue is Full \n");
    }
    else
    {
        q->r = (q->r + 1)%q->size;
        q->arr[q->r] = val;
        printf("Enque Element %d \n" , val);
    }
}

// dequeue operation
int dequeue(struct CircularQueue *q)
{
    int a = -1;
    if (IsEmpty(q))
    {
        printf("This queue is Empty\n");
    }
    else
    {
        q->f=(q->f+1)%q->size;
        a = q->arr[q->f];
    }
    return a;
}

int main()
{
    struct CircularQueue q;
    q.size = 4;
    q.f = q.r = 0;
    q.arr = (int *)malloc(q.size * sizeof(int));

    // Enqueue few element
    enqueue(&q, 234);
    enqueue(&q, 889);
    enqueue(&q, 35);
    // enqueue(&q, 37);

    printf("dequeue of Element:- %d \n", dequeue(&q));
    printf("dequeue of Element:- %d \n", dequeue(&q));
    printf("dequeue of Element:- %d \n", dequeue(&q));

    enqueue(&q, 300);

    if (IsEmpty(&q))
    {
        printf("Queue is Empty! \n");
    }
    else
    {
        printf("Queue is not empty! \n");
    }
    if (IsFull(&q))
    {
        printf("Queue is Full! \n");
    }
    else
    {
        printf("Queue is not Full! \n");
    }

    return 0;
}