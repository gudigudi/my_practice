#ifndef BYTECONVERTER_H
#define BYTECONVERTER_H

#include "ui_byteconverter.h"
#include <QDialog>

class QLineEdit;

class ByteConverter : public QDialog
{
    Q_OBJECT

public:
    ByteConverter();

private:
    QLineEdit* decEdit;
    QLineEdit* hexEdit;
    QLineEdit* binEdit;

private slots:
    void decChanged(const QString&);
    void hexChanged(const QString&);
    void binChanged(const QString&);
};

#endif // BYTECONVERTER_H
