#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    model = new QStringListModel(this);
    QStringList list;

    list << "apple" << "banana" << "orange";
    model->setStringList(list);

    ui->listView->setModel(model);
    ui->comboBox->setModel(model);
    ui->listView->setEditTriggers(QAbstractItemView::AnyKeyPressed | QAbstractItemView::DoubleClicked);
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
    int row = model->rowCount();
    model->insertRows(row, 1);
    QModelIndex index = model->index(row);
    ui->listView->edit(index);
}

void MainWindow::on_pushButton_2_clicked()
{
    int row = ui->listView->currentIndex().row();
    model->insertRows(row, 1);
    QModelIndex index = model->index(row);

    ui->listView->setCurrentIndex(index);
    ui->listView->edit(index);
}

void MainWindow::on_pushButton_3_clicked()
{
    model->removeRows(ui->listView->currentIndex().row(), 1);
}
