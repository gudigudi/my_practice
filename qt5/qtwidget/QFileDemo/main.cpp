#include <QCoreApplication>
#include "file_process.h"

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    QString filename("/home/gudi/hello.txt");
    WriteFile (filename);
    ReadFile (filename);

    return a.exec();
}
