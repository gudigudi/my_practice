#include "download.h"
#include <QUrl>
#include <QtNetwork/QNetworkRequest>
#include <QtNetwork/QNetworkReply>
#include <QFile>
#include <QTextStream>

Download::Download(QObject *parent) : QObject(parent)
{
}

void Download::do_download()
{
    QNetworkAccessManager *manager = new QNetworkAccessManager(this);

    connect(manager, SIGNAL(finished(QNetworkReply*)),
            this, SLOT(replyFinished(QNetworkReply*)));

    manager->get(QNetworkRequest(QUrl("http://www.baidu.com")));
}

void Download::replyFinished(QNetworkReply *reply)
{
    if (reply->error() == QNetworkReply::NoError) {
        qDebug() << "Connected.";
        QString text = reply->readAll();
        qDebug() << "Size: " << text.size();
        QFile file ("/home/gudi/web.txt");
        if (file.open(QIODevice::WriteOnly)) {
            QTextStream out(&file);
            out << text;
            out.flush();
            qDebug() << "Download Web Succes";
            file.close();
        } else {
            qDebug() << "Failed to open file";
        }
    } else {
        qDebug() << "ERROR!";
        qDebug() << reply->errorString();
    }
    reply->deleteLater();
}
