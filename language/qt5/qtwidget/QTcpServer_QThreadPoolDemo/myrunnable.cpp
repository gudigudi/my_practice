#include "myrunnable.h"

MyRunnable::MyRunnable()
{
}

void MyRunnable::run()
{
    if (!SocketDescriptor) return;
    QTcpSocket setSocketDescriptor(SocketDescriptor);

}
