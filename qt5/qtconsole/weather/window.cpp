#include "window.h"
#include <QPushButton>
#include <QApplication>

Window::Window(QWidget *parent) : QWidget(parent)
{
    setFixedSize(300, 200);

    m_button = new QPushButton("Hello World", this);
    m_button->setGeometry(10, 10, 150, 40);
    m_button->setCheckable(true);

    m_counter = 0;

    connect(m_button, SIGNAL(clicked()),
            this, SLOT(close(slotButtonClicked())));
    window::connect(this, SIGNAL(counterReached()),
            QApplication::instance(), SLOT(quit()));
}

void Window::slotButtonClicked(bool checked)
{
    if (checked) {
        m_button->setText("Checked");
    } else {
        m_button->setText("Hello World");
    }
    m_counter++;
    if (m_counter == 10) {
        emit counterReached();
    }
}

void Window::aboutQt()
{
    QApplication::aboutQt();
}

