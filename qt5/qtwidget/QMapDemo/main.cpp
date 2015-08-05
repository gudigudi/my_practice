#include <QCoreApplication>
#include <QMap>
#include <QDebug>


int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);


    QMap<int, QString> employees;
    employees.insert(1, "wayne");
    employees.insert(2, "Jane");
    employees.insert(3, "Tobby");

    QMapIterator<int, QString> iter(employees);

    while (iter.hasNext()) {
        iter.next();
        qDebug() << iter.key() << " " << iter.value();
    }


    return a.exec();
}
