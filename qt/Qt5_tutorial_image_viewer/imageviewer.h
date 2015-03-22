#ifndef IMAGEVIEWER_H
#define IMAGEVIEWER_H

#include "ui_imageviewer.h"

namespace Ui {
class ImageViewer;
}

class ImageViewer : public QMainWindow
{
    Q_OBJECT

public:
    explicit ImageViewer(QWidget *parent = 0);
    ~ImageViewer();

private:
    Ui::ImageViewer *ui;
    QLabel *imageLabel;
    QScrollArea *scrollArea;
};

#endif // IMAGEVIEWER_H
