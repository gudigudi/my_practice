#include "mainwindow.h"
#include <QApplication>
#include <QPushButton>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
//    MainWindow w;
//    w.show();

    QPushButton button("Quit");
    button.show();

    QObject::connect (&button, SIGNAL(clicked()),
                      &a, SLOT(quit()));

    return a.exec();
}
