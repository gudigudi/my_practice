/* show how to display components in hlayout and vlayout
 */
#include <QApplication>
#include <QPushButton>
#include <QVBoxLayout>
#include <QHBoxLayout>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    /* Create a widget
     */
    QWidget *window = new QWidget;
    window->setWindowTitle("My App");

    /* Create normal button
     */
    QPushButton *button1 = new QPushButton("one");
    QPushButton *button2 = new QPushButton("two");
    QPushButton *button3 = new QPushButton("three");

    /* Create HBoxLayout
     * Add the 3 buttons to the layout
     * Set widget in the layout
     */
    QHBoxLayout *hlayout = new QHBoxLayout;
    hlayout->addWidget(button1);
    hlayout->addWidget(button2);
    hlayout->addWidget(button3);
    window->setLayout(hlayout);


    window->show();

    return app.exec();
}

