#include <QCoreApplication>
#include <QList>
#include <QDebug>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);


    QList<int> list;

    for (int i = 0; i != 10; i++) {
        list.push_back(i);
    }

    list.removeOne(5);

    foreach (int element, list) {
        qDebug() << element;
    }

    return a.exec();
}
