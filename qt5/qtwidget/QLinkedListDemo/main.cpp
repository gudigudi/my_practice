#include <QCoreApplication>
#include <QLinkedList>
#include <QDebug>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);


    QLinkedList<int> list;

    list << 1 << 3 << 5;

    foreach (int i, list) {
        qDebug() << i;
    }


    return a.exec();
}
