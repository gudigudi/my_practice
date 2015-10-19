#ifndef DIALOG_H
#define DIALOG_H

#include <QDialog>
#include <QStandardItemModel>
#include "delegate.h"

namespace Ui {
class Dialog;
}

class Dialog : public QDialog
{
    Q_OBJECT

public:
    explicit Dialog(QWidget *parent = 0);
    ~Dialog();

private:
    Ui::Dialog *ui;

    QStandardItemModel *model;
    delegate *mydelegate;
};

#endif // DIALOG_H
