#ifndef MYTHREAD_H
#define MYTHREAD_H

#include <QThread>

class MyThread : public QThread
{
    Q_OBJECT
public:
    explicit MyThread(QObject *parent = 0);

    QString name;
    bool stop;

signals:

public slots:
    void run();
};

#endif // MYTHREAD_H
