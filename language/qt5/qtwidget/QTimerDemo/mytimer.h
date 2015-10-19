#ifndef MYTIMER_H
#define MYTIMER_H


#include <QObject>
#include <QTimer>


class mytimer : public QObject
{
    Q_OBJECT
public:
    mytimer();

public slots:
    void myslots();

private:
    QTimer *timer;
};

#endif // MYTIMER_H
