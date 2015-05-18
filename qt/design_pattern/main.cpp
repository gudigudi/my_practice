#include <QApplication>
#include <QString>
#include <QLabel>
#include <QWidget>
#include <QDebug>
#include <QTextStream>

int main(int argc, char *argv[])
{
    QApplication myapp(argc, argv);
    QWidget wid;
    qDebug() << "sizeof widget: " << sizeof(wid)
             << " sizeof qapplication: " << sizeof(myapp);
    QString message;
    QTextStream buf(&message);
    buf << "A QWidget is " << sizeof(wid)
        << " bytes. \nA QObject is " << sizeof(QObject)
        << " bytes. \nA OApplication is " << sizeof(myapp)
        << " bytes.";
    qDebug() << message;
    QLabel label(message);
    label.show();
    return myapp.exec();
}
