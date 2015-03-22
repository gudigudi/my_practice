#include "byteconverter.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    ByteConverter bc;
    bc.setAttribute (Qt::WA_QuitOnClose);
    bc.show();

    return a.exec();
}
