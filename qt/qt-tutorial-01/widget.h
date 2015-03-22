#ifndef WIDGET_H
#define WIDGET_H

#include "ui_widget.h"

class widget : public QWidget, private Ui::widget
{
    Q_OBJECT

public:
    explicit widget(QWidget *parent = 0);

    void setName(const QString &name);
    QString name() const;
};

#endif // WIDGET_H
