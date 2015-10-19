#ifndef DELEGATE_H
#define DELEGATE_H

#include <QItemDelegate>
#include <QModelIndex>
#include <QSpinBox>
#include <QObject>
#include <QSize>


class delegate : public QItemDelegate
{
    Q_OBJECT
public:
    explicit delegate(QObject *parent=0);

    QWidget * createEditor(QWidget *parent, const QStyleOptionViewItem &option, const QModelIndex &index) const;
    void setEditorData(QWidget *editor, const QModelIndex &index) const;
    void setModelData(QWidget *editor, QAbstractItemModel *model, const QModelIndex &index) const;
    void updateEditorGeometry(QWidget *editor, const QStyleOptionViewItem *option, const QModelIndex &index) const;

signals:

public slots:

};

#endif // DELEGATE_H
