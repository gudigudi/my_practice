#include "mainwindow.h"
#include "mydialog.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    setupUi(this);
}

void MainWindow::on_actionNew_Window_triggered()
{
    MyDialog mDialog;
    mDialog.setModal(true);
    mDialog.exec();
}
