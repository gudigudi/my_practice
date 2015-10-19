#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
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

void MainWindow::on_actionNew_triggered()
{
    QMessageBox::information(this, "title", "new");
}

void MainWindow::on_actionOpen_triggered()
{
    QMessageBox::information(this, "title", "open");
}

void MainWindow::on_actionSave_triggered()
{
    QMessageBox::information(this, "title", "save");
}
