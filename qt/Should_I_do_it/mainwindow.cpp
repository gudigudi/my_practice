#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QWidget>

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

void MainWindow::on_pushButton_clicked()
{
    QRadioButton *btn1 = new QRadioButton("hello", this);
    QWidgetWindow widgetWindow = new QWidgetWindow();
    widgetWindow.show();
}
