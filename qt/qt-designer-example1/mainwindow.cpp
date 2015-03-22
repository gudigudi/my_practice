#include "mainwindow.h"
#include <QString>

const static double PI=3.1416;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    setupUi(this);
}

void MainWindow::on_pushButton_clicked()
{
    bool ok;
    QString tempStr;
    QString valueStr = this->lineEdit->text();
    int valueInt = valueStr.toInt (&ok);
    double area = valueInt * valueInt * PI;  // 计算圆的面积
    this->lineEdit_2->setText(tempStr.setNum (area));
}
