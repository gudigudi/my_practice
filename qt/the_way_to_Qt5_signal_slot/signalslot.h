#ifndef SIGNALSLOT_H
#define SIGNALSLOT_H

#include "ui_signalslot.h"

class SignalSlot : public QMainWindow, private Ui::SignalSlot
{
    Q_OBJECT

public:
    explicit SignalSlot(QWidget *parent = 0);
};

#endif // SIGNALSLOT_H
