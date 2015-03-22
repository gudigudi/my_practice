#include "mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    setWindowTitle (tr("Main Window"));

    QAction *openAction = new QAction(QIcon("./image/doc-open"), tr("&Open..."), this);
    openAction->setShortcuts(QKeySequence::Open);
    openAction->setStatusTip(tr("Open an existing file"));
    connect (openAction, &QAction::triggered, this, &MainWindow::open);

    QMenu *file = menuBar ()->addMenu (tr("&File"));
    file->addAction(openAction);
}
MainWindow::~MainWindow()
{
}

void MainWindow::open()
{
    QDialog dialog;
    dialog.setWindowTitle(tr("Hello, dialog"));
    dialog.exec();
}
