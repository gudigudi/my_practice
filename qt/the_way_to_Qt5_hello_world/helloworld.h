#ifndef HELLOWORLD_H
#define HELLOWORLD_H

#include "ui_helloworld.h"

class HelloWorld : public QMainWindow, private Ui::HelloWorld
{
    Q_OBJECT

public:
    explicit HelloWorld(QWidget *parent = 0);
};

#endif // HELLOWORLD_H
