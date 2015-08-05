#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent), ui(new Ui::MainWindow) {
  ui->setupUi(this);

  ui->treeWidget->setColumnCount(2);
  ui->treeWidget->setHeaderLabels(QStringList() << "Item"
                                                << "Type");

  QTreeWidgetItem *root = AddRoot("China", "Country");
  AddChild(root, "ZheJiang", "Province");
  AddChild(root, "JiangSu", "Province");
  AddChild(root, "ShangDong", "Province");
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

QTreeWidgetItem *MainWindow::AddRoot(const QString &name,
                                     const QString &description) {
  QTreeWidgetItem *root = new QTreeWidgetItem(ui->treeWidget);
  // add description information
  root->setText(0, name);
  root->setText(1, description);
  return root;
}

void MainWindow::AddChild(QTreeWidgetItem *parent, const QString &name,
                          const QString &description) {
  QTreeWidgetItem *child = new QTreeWidgetItem(parent);
  child->setText(0, name);
  child->setText(1, description);
}

void MainWindow::on_pushButton_clicked()
{
    ui->treeWidget->currentItem()->setBackgroundColor(0, Qt::green);
    ui->treeWidget->currentItem()->setBackgroundColor(1, Qt::red);
}
