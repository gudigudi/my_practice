#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    QString rootpath = "/home/gudi";
    dirModel = new QFileSystemModel(this);
    dirModel->setRootPath(rootpath);
    dirModel->setFilter(QDir::NoDotAndDotDot | QDir::AllDirs);
    ui->treeView->setModel(dirModel);

    fileModel = new QFileSystemModel(this);
    fileModel->setRootPath(rootpath);
    fileModel->setFilter(QDir::NoDotAndDotDot | QDir::AllDirs);
    ui->listView->setModel(fileModel);
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

void MainWindow::on_treeView_clicked(const QModelIndex &index)
{
    QString rootPath = dirModel->fileInfo(index).absolutePath();
    ui->listView->setRootIndex(fileModel->setRootPath(rootPath));
}
