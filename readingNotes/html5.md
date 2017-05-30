## HTML5的特点 ##

1. 新元素: header, footer, nav, section, article; 表单控件: calendar, date, time, email, url, search
2. 新属性
3. 完全支持CSS3
4. Video和Audio: <video>, <audio>
5. 2D/3D制图: <canvas>, 内联SVG, CSS3 2D/3D
6. 本地存储
7. 本地SQL数据
8. Web应用

## HTML5 element reference ##
* basic element:  
  * h1, h2, h3, h4, h5, h6 
  * p 
  * br, hr 
  * a 
  * img

## HTML 速查列表 ##

**HTML comment**
`<!--...-->`

**HTML basic template**
``` html
<!DOCTYPE html>
<html>
<head>
  <title>document title</title>
</head>

<body>
the visual document 
</body>

</html>
```

**HTML5 basic template**
``` html
<!DOCTYPE html>
<html>
<head>
  <title>document title</title>
</head>

<body>
  <header>header</header>
  <nav>navigation</nav>
  <footer>footer</footer>
</body>

</html>
```

**HTML basic tags**
``` html 
<h1>the largest heading</h1>
<h2>...</h2>
<h3>...</h3>
<h4>...</h4>
<h5>...</h5>
<h6>the smallest heading</h6>
```

**formatting**
``` html 
<b>粗体文本</b>
<code>计算机代码</code>
<em>强调</em>
<i>斜体文本</i>
<kbd>键盘输入</kdb>
<pre>预格式化文本</pre>
<small>更小的文本</small>
<strong>重要的文本</strong>
```

**link**
normal link : `<a href="http://www.example.com/"></a>`  
image  link : `<a href="http://www.example.com/"><img src="URL" alt="替换文本>"</a>`  
email  link : `<a href="mailto:webmaster@example.com">send email</a>`  

**image**
`<img src="URL" alt="替换文本" height="42" width="42">`

**section**
``` html 
<div>文档中的块级元素</div>
<span>文档中的内联元素</span>
```

**list**
``` html 
<!-- 无序列表 -->
<ul>
  <li>项目1</li>
  <li>项目2</li>
</ul>
<!-- 有序列表 -->
<ol>
  <li>第1项</li>
  <li>第2项</li>
</ol>
<!-- 定义列表 -->
<dl>
  <dt>项目1</dt>
    <dd>描述项目1</dd>
  <dt>项目2</dt>
    <dd>描述项目2</dd>
</dl>
```

**table**
``` html 
<table border="1">
  <tr>
    <th>表格标题</th>
    <th>表格标题</th>
  </tr>
  <tr>
    <td>表格数据</td>
    <td>表格数据</td>
  </tr>
</table>
```

**iframe**
`<iframe src="demo_iframe.html"></iframe>`

**form**
``` html
<form action="action.php" method="post/get">

  <input type="text" name="email" size="40" maxlength="50">
  <input type="password">
  <input type="checkbox" checked="checked">
  <input type="radio" checked="checked">
  <input type="submit" value="Send">
  <input type="reset">
  <input type="hidden">

  <select>
    <option>apple</option>
    <option selected="selected">banana</option>
    <option>lime</option>
  </select>

  <textarea name="comment" rows="60" cols="20"></textarea>

</form>
```

**NEW in HTML5**
```html 
<article>
一个文章区域
</article>
<aside>
页面的侧边栏内容
</aside>
<audio controls>
  <source src="ogg.ogg" type="audio/ogg">
  <source src="mp3.mp3" type="audio/mpeg">
</audio>
```
**special tags**
``` html
<address>your address and secial information</address>
```

