#include <QCoreApplication>
#include <QHash>
#include <QDebug>


int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);


    QHash<int, QString> Employee;

    Employee.insert(1, "wayne");
    Employee.insert(2, "Jane");
    Employee.insert(3, "Roshan");

    QHashIterator<int ,QString> iter(Employee);

    while (iter.hasNext()) {
        iter.next();
        qDebug() << iter.key() << " " << iter.value();
    }

    return a.exec();
}
