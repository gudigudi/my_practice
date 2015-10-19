typedef int semaphore;
semaphore mutex = 1;
semaphore db = 1;
int rc = 0;

void reader(void)
{
    while(TRUE) {
        down(&mutex);
        rc++;
        if (rc == 1) down(&db);
        up(&mutex);
        read_data_base();
        down(&mutex);
        rc--;
        if (rc == 0) up(&db);
        up(&mutex);
        use_data_read();
    }
}

void write(void)
{
    while(TRUE) {
        think_up_data();
        down(&db);
        write_data_base();
        up(&db);
    }
}
