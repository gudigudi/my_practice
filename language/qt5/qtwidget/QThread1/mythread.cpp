#include "mythread.h"
#include <QDebug>
#include <QMutex>

MyThread::MyThread(QObject *parent) :
    QThread(parent)
{

}

void MyThread::run()
{
    qDebug() << name << " Running";
    for (int i = 0; i != 1000; i++) {
        QMutex mutex;
        mutex.lock();
        if (stop) {
            mutex.unlock();
            break;
        }
        mutex.unlock();
        qDebug() << name << i;
    }
}
