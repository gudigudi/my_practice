#include "imageviewer.h"

ImageViewer::ImageViewer(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::ImageViewer)
{
    ui->setupUi(this);

    QImage image(":/image/463871.jpg");
    ui->label->setPixmap(QPixmap::fromImage (image));

    imageLabel = new QLabel;
    imageLabel->setBackgroundRole (QPalette::Base);
    imageLabel->setSizePolicy (QSizePolicy::Ignored, QSizePolicy::Ignored);
    imageLabel->setScaledContents (true);

    scrollArea = new QScrollArea;
    scrollArea->setBackgroundRole (QPalette::Dark);
    scrollArea->setWidget (scrollArea);

    setWindowTitle (tr("Image Viewer"));
    resize(500, 400);

}

ImageViewer::~ImageViewer()
{
    delete ui;
}
