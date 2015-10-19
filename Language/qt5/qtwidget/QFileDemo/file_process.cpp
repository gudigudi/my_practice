#include "file_process.h"
#include <QString>
#include <QDebug>
#include <QTextStream>
#include <QFile>

// read file function
void ReadFile (const QString &filename)
{
    QFile my_file(filename);
    if (!my_file.open (QIODevice::ReadOnly | QIODevice::Text)) {
        qDebug() << "Could not open file for Reading";
        return;
    }

    QTextStream outText(&my_file);
    qDebug() << outText.readAll ();
    my_file.close ();
}

void WriteFile (const QString &filename)
{
    QFile my_file(filename);
    if (!my_file.open (QIODevice::WriteOnly | QIODevice::Text)) {
        qDebug() << "Could not open file for writing";
        return;
    }

    QTextStream in(&my_file);
    in << "Hello World!";
    my_file.flush ();
    my_file.close ();
}
