#include <QCoreApplication>
#include "download.h"

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    Download download;
    download.do_download();

    return a.exec();
}
