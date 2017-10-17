#!/usr/bin/python
# -*- coding: utf-8 -*-


import os
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

print "** MonkeyRunner Start"

package_name = "com.gudigudigudi.mdtemplate"
activity_name = package_name + ".materialdesign.CardViewActivity"

# 判断截图目录是否存在，如果没有就创建
if not os.path.exists("screenshots"):
    print "creating the screenshots directory"
    os.makedirs("screenshots")

device = MonkeyRunner.waitForConnection()

# 测试应用，如果找不到就安装
apk_path = device.shell("pm path " + package_name)
if apk_path.startswith("package:"):
    print "App Found"
else:
    print "Installing App"
    device.installPackage(package_name + ".apk")

print "Starting CardViewActivity"
device.startActivity(component=package_name + "/" + activity_name)

# 获取一个截图
MonkeyRunner.sleep(1)
result = device.takeSnapshot()
result.writeToFile('./screenshots/monkeyrunner_ss.png', 'png')
print "Screenshot Taken"

# 模拟菜单按钮的单击事件
device.press("KEYCODE_MENU", MonkeyDevice.DOWN_AND_UP)

print "** MonkeyRunner Finish"
