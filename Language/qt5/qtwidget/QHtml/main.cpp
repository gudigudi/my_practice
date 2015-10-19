/*
 * Create a label
 * display a string by html-format
 */

#include<QApplication>
#include<QLabel>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);

    /* Create a label ...
     */
    QLabel *label = new QLabel("<h2>Hello</h2> <font color=red><h2>World!</h2></font>");
    label->show();

    return app.exec();
}
