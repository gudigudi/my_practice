#include <QAction>
#include <QMenuBar>
#include <QMessageBox>
#include <QStatusBar>
#include <QToolBar>

#include "mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    setWindowTitle ("Main Window");

    openAction = new QAction(QIcon("/image/doc-open"), tr("&Open..."), this);
    openAction->setShortcut (QKeySequence::Open);
    openAction->setStatusTip (tr("Open an existing file"));
    connect (openAction, &QAction::triggered, this, &MainWindow::open);

    QMenuBar *file = menuBar ()->addMenu (tr("&File"));
    file->addAction(openAction);

    QToolBar *toolBar = addToolBar (tr("&File"));
    toolBar->addAction(openAction);

    statusBar ();
}

MainWindow::~MainWindow()
{
}

void MainWindow::open ()
{
    QMessageBox::information (this, tr("Information"), tr("Open"))
}
