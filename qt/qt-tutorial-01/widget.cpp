#include "widget.h"

widget::widget(QWidget *parent) :
    QWidget(parent)
{
    setupUi(this);
}

void widget::setName (const QString &name)
{
    this->lineEdit->setText (name);
}

QString widget::name () const
{
    return this->lineEdit->text ();
}
