
// 引入依赖
var express = require('express');
var utility = require('utility');

// 简历`express`实例
var app = express();

app.get('/', function (req, res) {
  // 从`req.query`中取出`q`参数
  var q = req.query.q;

  // 调用`utility.md5`方法, 得到md5之后的值
  var md5Value = utility.md5(q);

  res.send(md5Value);
});

app.listen(3000, function (req, res) {
  console.log('app is running at port 3000');
});
