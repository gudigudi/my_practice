#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    StatusLabel = new QLabel(this);
    StatusProgress = new QProgressBar(this);
    StatusLabel->setText("ProgressBar: ");
    StatusProgress->setTextVisible(false);
    StatusProgress->setValue(0);
    ui->statusBar->addPermanentWidget(StatusLabel);
    ui->statusBar->addPermanentWidget(StatusProgress);
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


void MainWindow::on_actionDo_something_triggered()
{
    StatusProgress->setValue(StatusProgress->value() % 100 + 10);
}
