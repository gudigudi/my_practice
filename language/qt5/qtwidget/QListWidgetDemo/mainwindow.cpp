#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QListWidgetItem>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    // Add item to listwidget
    for (int i = 0; i < 10; i++) {
        ui->listWidget->addItem (QString::number (i) + " Item");
    }
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::changeEvent(QEvent *e)
{
    QMainWindow::changeEvent(e);
    switch (e->type()) {
    case QEvent::LanguageChange:
        ui->retranslateUi(this);
        break;
    default:
        break;
    }
}

void MainWindow::on_pushButton_clicked()
{
    QListWidgetItem *item = ui->listWidget->currentItem ();
    item->setText ("Selected");
    item->setTextColor (Qt::red);
    item->setBackgroundColor (Qt::green);
}
