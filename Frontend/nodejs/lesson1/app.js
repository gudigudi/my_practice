
// 引入`express`模块，并将它赋予`express`这个模块
var express = require('express');

// 调用`express`实例，它是一个函数，不带参数调用时，会返回一个`express`实例
// 将这个变量赋予`app`变量
var app = express();

// app本身有很多方法，其中包括最常用的`get`, `post`, `put/patch`, `delete`.
// 在这里我们调用其中的`get`方法. 为我们的`/`路径指定一个`handler`函数.
// 这个`handler`函数会接收`req`和`res`两个对象. 它们分别是请求的`request`和
// `response`
// `request`中包含了浏览器传来的各种信息，比如`query`, `body`, `headers`
// `res`对象用来定制向浏览器输出的信息. 这里我们调用它的`send`方法, 向浏览器
// 发送一个字符串"Hello World"
app.get('/', function (req, res) {
    res.send('Hello World');
});

// 让`app`监听本地的3000端口.
// 这里的第二个函数是个回调函数, 会在`listen`成功之后执行
// 我们这里执行了一个命令行输出操作, 告诉我们监听动作已完成
app.listen(3000, function() {
    console.log('app is listening at port 3000');
});



