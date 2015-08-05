#include <QCoreApplication>
#include <QDebug>
#include <iostream>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);


    QString sentence = "Hello World";
    qDebug() << sentence;

    std::string new_sentence = "\"Hello World\"";
    std::cout << new_sentence << std::endl;

    QTextStream cout(stdout);
    cout << sentence << endl;

    return a.exec();
}
