/*
 * Show usage of QDir
 */
#include <QCoreApplication>
#include <QDir>
#include <QDebug>
#include <QFileInfo>

int main(int argc, char *argv[]) {
  QCoreApplication a(argc, argv);

  QString path("/home/gudi");
  QDir m_dir(path);

  // is the dir exists
  if (m_dir.exists()) {
    // get dir info by foreach
    foreach (QFileInfo item, m_dir.entryInfoList()) {
      if (item.isDir()) {
        qDebug() << "Dir: " << item.absoluteFilePath();
      }
      if (item.isFile()) {
        qDebug() << "File: " << item.absoluteFilePath();
      }
    }
  } else {
    qDebug() << "The Directory doesn't exist";
  }

  return a.exec();
}
