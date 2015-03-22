#ifndef NOTEPAD_H
#define NOTEPAD_H

#include <QMainWindow>

#include "ui_notepad.h"

namespace Ui {
class NotePad;
}

class NotePad : public QMainWindow, private Ui::NotePad
{
    Q_OBJECT

public:
    explicit NotePad(QWidget *parent = 0);
    ~NotePad();

private:
    Ui::NotePad *ui;
};

#endif // NOTEPAD_H
