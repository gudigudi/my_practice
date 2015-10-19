#include "sockettest.h"

SocketTest::SocketTest(QObject *parent) :
    QObject(parent)
{
}

void SocketTest::Connect()
{
    socket = new QTcpSocket(this);
    socket->connectToHost("voidrealms.com", 80);

    if (socket->waitForConnected(3000)) {
        qDebug() << "Connect";
        socket->write("Hello Server\n\n");
        socket->waitForReadyRead(3000);
        qDebug() << "Reading :" << socket->bytesAvailable();
        qDebug() << socket->readAll();
        socket->close();
    } else {
        qDebug() << "Not Connect";
    }
}
