#ifndef MYDIALOG_H
#define MYDIALOG_H

#include "ui_mydialog.h"

class MyDialog : public QDialog, private Ui::MyDialog
{
    Q_OBJECT

public:
    explicit MyDialog(QWidget *parent = 0);
};

#endif // MYDIALOG_H
