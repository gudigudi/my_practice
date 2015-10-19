#include "dialog.h"
#include "ui_dialog.h"

Dialog::Dialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Dialog)
{
    ui->setupUi(this);

    ui->pushButton->setToolTip("Hello <font color='red'><b>World</b></font>"
                               "<ul>"
                               "<li>Item 1</li>"
                               "<li>Item 2</li>"
                               "<li>Item 3</li>");

    ui->pushButton_2->setToolTip("<img src='./1.png'>KungFuPanda");
}

Dialog::~Dialog()
{
    delete ui;
}
