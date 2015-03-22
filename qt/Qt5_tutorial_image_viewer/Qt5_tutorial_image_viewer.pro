#-------------------------------------------------
#
# Project created by QtCreator 2015-01-13T21:39:49
#
#-------------------------------------------------

QT       += core gui
QT       += printsupport

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Qt5_tutorial_image_viewer
TEMPLATE = app


SOURCES += main.cpp\
        imageviewer.cpp

HEADERS  += imageviewer.h

FORMS    += imageviewer.ui

RESOURCES += \
    image.qrc
