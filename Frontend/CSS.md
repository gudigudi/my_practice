## 常用CSS属性

1. CSS背景属性(background)
2. CSS边框属性(border &outline)
3. CSS文本属性(text)
4. CSS字体属性(font)
5. CSS外边距属性(margin)
6. CSS内边距属性(padding)
7. CSS列表属性(list)
8. CSS尺寸属性(dimension)
9. CSS定位属性(positioning)
10. CSS表格属性(table)

### 1. CSS背景属性(background)
属性|描述
-|-
background|所有背景设置
background-attachment|设置背景图像是否固定或者随着页面的其余部分滚动
background-color|
background-image|
background-position|
background-repeat|

### 2. CSS边框属性(border &outline)
属性|描述
-|-
border|所有边框属性设置
border-color|
border-style|
border-width|
border-top|
border-top-color|
border-top-style|
border-top-width|
border-right|
border-right-color|
border-right-style|
border-right-width|
border-bottom|
border-bottom-color|
border-bottom-style|
border-bottom-width|
border-left|
border-left-color|
border-left-style|
border-left-width|
outline|
outline-color|
outline-style|
outline-width|

### 3. CSS文本属性(text)
属性|描述
-|-
color|
direction|规定文本的方向/书写方向
letter-spacing|字符间距
line-spacing|行高
text-align|水平对齐方式
text-decoration|装饰效果
text-indent|首行缩进
text-shadow|阴影效果
text-tranform|大小写
white-space|规定处理元素中的空白,换行/不换行
word-spacing|单词间距

### 4. CSS字体属性(font)
属性|描述
-|-
font|所有字体属性
font-family|
font-size|
font-stretch|收缩拉伸
font-style|
font-weight|粗细

### 5. CSS外边距属性(margin)
属性|描述
-|-
margin|所有外边框属性
margin-top|
margin-right|
margin-bottom|
margin-left|

### 6. CSS内边距属性(padding)
属性|描述
-|-
padding|所有内边框属性
padding-top|
padding-right|
padding-bottom|
padding-left|

### 7. CSS列表属性(list)
属性|描述
-|-
list-style|所有列表属性
list-style-image|
list-position|
list-style-type|

### 8. CSS尺寸属性(dimension)
属性|描述
-|-
height|
width|
max-height|
max-width|
min-height|
min-width|

### 9. CSS定位属性(positioning)
属性|描述
-|-
top|
right|
bottom|
left|
clear|
clip|裁剪
cursor|
display|
float|
overflow|
position|
vertical-align|
visibility|
z-index|

### 10. CSS表格属性(table)
属性|描述
-|-
border-collapse|是否合并表格边框
border-spacing|相邻单元格边框之间的距离
caption-side|表格标题的位置
empty-cells|是否显示表格中的空单元格上的边框和背景
table-layout|表格的布局算法

## 盒模式(box model):margin>>border>>padding>>content

## CSS定位方式
* display
* position

### display

属性|描述
-|-
block|宽高可以自行设置, 默认宽度由父容器决定, 默认高度有内容决定。自己独占一行。
inline|宽度和高度都有内容决定, 与其他元素共占一行。
inline-block|宽度可以自行设置, 类似block, 但是与其他元素共占一行, 类似inline。长用于设置垂直居中。
table-cell|让标签元素以表格单元格的形式呈现, 单元格有一些比较特殊的属性, 可以设置元素的垂直居中等。

### position
>元素在页面中的布局遵守一套文档流的方式, 默认的定位属性值为static。它其实是未被设置定位的。
>元素如果被定位了, 那么它的top,left,bottom,right值就会生效, 能设置定位的属性是relative,absolute和fixed。

属性|描述
-|-
relative(相对定位)|设置了相对定位之后, 通过修改top,left,bottom,right值, 元素会在自身文档流所在位置上被移动, 其他的元素则不会调整位置来弥补它偏离后剩下的空隙。
absolute(绝对定位)|设置了绝对定位之后, 元素脱离文档流, 其他的元素会调整位置来弥补它偏离后剩下的空隙。元素偏移是相对于是它最近的设置了定位属性(position值不为static)的元素。且如果元素为块级元素(display属性值为block), 那么它的宽度也会由内容撑开。
fixed(固定定位)|设置了固定定位之后, 元素相对的偏移的参考是可视窗口, 即使页面滚动, 元素仍然会在固定位置

## CSS选择器

### 基本选择器
* 通配符选择器(*)
* id选择器(\#ID)
* 类选择器(.className)
* 元素选择器(E)
* 后代选择器(E F)
* 子元素选择器(E > F)
* 相邻兄弟元素选择器(E + F)
* 群组选择器(selector1,selector2,...,selectorN)

### 属性选择器
* E[attr]: 只使用属性名, 但没有确定任何属性值
* E[attr="value"]: 指定属性名, 并指定了该属性的属性值
* E[attr~="value"]: 指定属性名, 并且具有属性值, 此属性值是一个词列表, 并且以空格隔开, 其中词列表中包含了一个value词, 而且等号前面的"〜"不能不写
* E[attr^="value"]: 指定了属性名, 并且有属性值, 属性值是以value开头的；
* E[attr$="value"]: 指定了属性名, 并且有属性值, 而且属性值是以value结束的；
* E[attr*="value"]: 指定了属性名, 并且有属性值, 而且属值中包含了value；
* E[attr|="value"]: 指定了属性名, 并且属性值是value或者以"value-"开头的值(比如说zh-cn);

### 伪类选择器
伪类选择器的形式就是:xxx, 比如:hover, :link, :nth。