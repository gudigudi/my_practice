#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QLabel>
#include <QProgressBar>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

protected:
    void changeEvent(QEvent *e);

private slots:
    void on_actionDo_something_triggered();

private:
    Ui::MainWindow *ui;
    QLabel *StatusLabel;
    QProgressBar *StatusProgress;
};

#endif // MAINWINDOW_H
