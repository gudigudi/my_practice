#ifndef DOWNLOAD_H
#define DOWNLOAD_H

#include <QObject>
#include <QtNetwork/QNetworkAccessManager>
#include <QDebug>

class Download : public QObject
{
    Q_OBJECT
public:
    explicit Download(QObject *parent = 0);
    void do_download();

signals:

public slots:
    void replyFinished(QNetworkReply *reply);

private:
    QNetworkAccessManager *manager;
};

#endif // DOWNLOAD_H
