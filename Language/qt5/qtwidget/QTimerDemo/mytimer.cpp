#include "mytimer.h"
#include <QTimer>
#include <QDebug>

mytimer::mytimer()
{
    timer = new QTimer(this);

    connect(timer, SIGNAL(timeout()), this, SLOT(myslots()));

    timer->start(1000);
}

void mytimer::myslots()
{
    qDebug() << "Timer Executed.";
}
