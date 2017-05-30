### 1. Android体系结构 ###

#### 1. __静态视角__的体系结构 ####

从上往下看，Android分为四层，分别是 __应用__层、__应用框架__层、
运行__环境__和系统__运行库__层、Linux__内核__层。

__应用框架层__为应用层提供API，它的主要组件为:

| 组件名称 | 功能描述 |
| -------- | -------- |
| View System         | 提供可扩展的用于构建应用程序UI的组件 
| Content             | 提供应用程序之间数据共享的接口       
| Resource Manager    | 提供非代码资源的访问接口             
| Notification Manager| 在状态栏中显示自定义的提示信息
| Activity Manager    | 管理应用程序生命周期并提供通用的导航回退功能
| Window Manager      | 管理所有的窗口程序
| Package Manager     | 提供程序安装包管理的接口

__系统运行库__组件

| 组件名称 | 功能描述 |
| -------- | -------- |
| libc(bionic)        | 相比标准libc库，在体积、运行效率、线程实现等方面都做了优化
| WebKit              | 
| Media FrameWork     | 多媒体框架
| SQLite              | 嵌入式数据库引擎
| OpenGL ES           | 3D图形渲染库
| FreeType            | 位图和矢量字体渲染引擎
| SurfaceManager      | 负责绘图的合成管理
| SGL                 | 2D图形渲染引擎
| SSL                 | 网络通信安全协议

#### 2. __动态视角__的体系结构 ####

Android分为两个交互的空间: __用户空间__和__内核空间__

__用户空间__ 

用户空间分为两个交互的子系统: Native子系统和Java子系统。两个子系统通过JNI技术
连接在一起，建立在内核空间之上。

Native子系统由NDK开发的App、应用框架层Native部分和标准库C/C++部分组成。

Java子系统由SDK开发的App、系统内置App、应用程序框架层Java部分、标准库的Java
接口组成。

__内核空间__

内核空间分为两部分: Linux内核和Android扩展支持

### 2. 框架基础JNI ###

JNI(Java Native Interface, Java本地接口)是Java平台上定义的一套标准的本地编程
接口。

### 3. Android启动过程的底层实现 ###

Android支持多种启动模式，主要有正常模式(normal mode)、安全模式(safe mode)、
恢复模式(recovery mode)、工厂模式(factory mode)、快速启动模式(fastboot mode)。
除正常模式外，都是刷机或者测试模式。

Android正常模式启动流程:

步骤1: 系统加电，执行bootloader，加载内核到内存中。

步骤2: 进入内核引导阶段，最后调用start_kernel进入内核启动模式，最终启动用户
空间的init程序。 

步骤3: init程序解析init.rc配置文件，开启系统守护进程。两个最重要的守护进程是
zygote(第一个虚拟机)和ServiceManager(Binder通信的基础)

步骤4: zygote虚拟机启动子进程system_server，而system_server开启了Android核心
系统服务并将核心系统服务添加到ServiceManager，然后系统进入systemReady状态。

步骤5: 在systemReady状态，ActivityManagerService与zygote中的Socket通信，通过
zygote启动Home应用，进入系统桌面。

### 4. 线程消息通信与异步处理 ###

Android在Java标准线程模型的基础上，提供了消息驱动机制，用于多线程之间的通信。
基于消息驱动机制的线程通信模型称为线程消息通信。

### 5. Package Manager的机制与实现 ###

Package Manager的功能主要包含以下部分:
1. __权限处理__: 包括对系统和应用定义的Permission和Permission Group信息的
增加、删除、查询和检查。
2. __包处理__: 扫描并安装和卸载APK包，查询包的UID、GID、包名、系统默认程序等
信息。
3. 比较两个包的Signatures信息是否相同。
4. 查询Activity、Provider、Receiver、Service信息。
5. 查询Application、Package、Resource、Shared Library、Feature信息。
6. Intent匹配。

### 6. Activity Manager的机制与实现 ###

Activity Manager提供的功能主要有以下几种:
* 启动或杀死应用程序进程。
* 启动并调度Activity生命周期。
* 启动并调度应用程序Service生命周期。
* 注册Broadcast Receiver，并接收和分发Broadcast。
* 启动并发布Content Provider。
* 调度task。
* 检查、授予、收回访问URI的权限。
* 处理应用程序crash。
* 调整进程调度优先级及策略(调整OOM adj)。
* 查询当前系统运行状态(包含Memory、Graphics、CPU、Database等)。

Activity Manager的组成主要分为以下六部分:
1. Binder接口: 由IBinder和Binder提供进程间通信的接口。
2. 服务接口: 由IInterface和IActivityManager提供系统服务的接口。
3. 服务中枢: 
4. 服务代理:
5. Client:
6. Server:

