#include "dialog.h"
#include "ui_dialog.h"
#include "linkfilter.h"
#include <QMessageBox>

Dialog::Dialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Dialog)
{
    ui->setupUi(this);

    LinkFilter *filter = new LinkFilter(this);
    this->installEventFilter(filter);
    connect(filter, SIGNAL(linkClicked(QString)), this, SLOT(ShowLink(QString)));
    ui->pushButton->setWhatsThis("For more information <a href='let the dog out for a while'>Click here</a>");
}

Dialog::~Dialog()
{
    delete ui;
}

void Dialog::ShowLink(const QString &Link)
{
    QMessageBox::information(this, "Title", Link);
}
