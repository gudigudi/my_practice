#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QMessageBox>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent), ui(new Ui::MainWindow) {
  ui->setupUi(this);
}

MainWindow::~MainWindow() { delete ui; }

void MainWindow::changeEvent(QEvent *e) {
  QMainWindow::changeEvent(e);
  switch (e->type()) {
  case QEvent::LanguageChange:
    ui->retranslateUi(this);
    break;
  default:
    break;
  }
}

void MainWindow::on_pushButton_clicked() {
  QMessageBox::information(this, "information", "This is information");
}

void MainWindow::on_pushButton_2_clicked() {
  QMessageBox::StandardButton reply;
  reply = QMessageBox::question(this, "question", "Do U like cat?",
                                QMessageBox::Yes | QMessageBox::No);
  if (reply == QMessageBox::Yes) {
    QMessageBox::information(this, "answer", "U love cat.");
  } else {
    QMessageBox::information(this, "answer", "U don't like cat.");
  }
}

void MainWindow::on_pushButton_3_clicked() {
  QMessageBox::warning(this, "warning", "This is warning");
}

void MainWindow::on_pushButton_4_clicked() {
  QMessageBox::question(this, "question", "U feel good?",
                        QMessageBox::Yes | QMessageBox::No |
                            QMessageBox::YesAll | QMessageBox::NoAll);
}
