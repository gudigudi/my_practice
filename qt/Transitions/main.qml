import QtQuick 2.2
import QtQuick.Controls 1.1
import QtQuick.Layouts 1.0
import QtQuick.Window 2.2

ApplicationWindow {
    visible: true
    width: 360
    height: 360
    title: qsTr("Hello World")

    menuBar: MenuBar {
        Menu {
            //iconSource: qsTr("")
            title: qsTr("File")
            MenuItem {
                text: qsTr("Exit")
                onTriggered: Qt.quit();
            }
        }
    }

    Rectangle {
        width: 360
        height: 360
        color: "#333333"
        MouseArea {
            anchors.bottomMargin: 0
            anchors.leftMargin: 0
            anchors.rightMargin: 0
            anchors.topMargin: 0
            anchors.fill: parent
            onClicked: {
                Qt.quit();
            }

            Image {
                id: image1
                x: 10
                y: 20
                width: 100
                height: 100
                source: "../../../../Pictures/证件照正面2.jpg"
            }
        }
    }
}
