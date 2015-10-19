#include <QCoreApplication>

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

//    QString str1 = "Welcome ";
//    str1 += "to you!";
//    QString str2 = "Hello, ";
//    str2 += "World!";

//    QString str1 = "Welcome ";
//    QString str2 = "to ";
//    str1.append(str2);
//    str1.append("you! ");

//    QString str;
//    str.sprintf("%s", "Welcome ");
//    str.sprintf("%s", "to you! ");
//    str.sprintf("%s %s", "Welcome ", "to you! ");

//    QString str;
//    str = QString("%1 was born in %2.").arg("John").arg(1982);

//    QString str = " Welcome \t to \n you!   ";
//    str = str.trimmed();

//    QString str = "Welcome to you! ";
//    str.startsWith("Welcome", Qt::CaseSensitive);
//    str.startsWith("you", Qt::CaseSensitive);
//    str.contains("Welcome", Qt::CaseSensitive);

    QString str = "125";
    bool ok;
    int hex = str.toInt(&ok, 16);
    int dec = str.toInt(&ok, 10);

    return a.exec();
}











