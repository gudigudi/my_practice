### 1. 数据库管理概论: 
>关系型数据库: 以table为基础结构 > 每一行是一个record, 每一列是一个field

键: 表中拥有特别意义的字段称为键(key)
* 主键(primary key): 表中的某个字段，其字段内容皆不相同，可以用来指出表中的每一行，且不会重复
* 候选键(condidate key): 有资格成为主键的字段，便成为表的候选键，作为替代键(alternate key)
* 连接键(concatenated key): 在表中没有一个适合的字段可以当做主键，此时可以找到由一个以上的字段组合而成的主键， 
称为连接键或复合键
* 外部键(foreign key): 在关系型数据库中，通常一个数据库内会建立一个以上的表，这些表相互之间可以建立关联， 
建立出关联后即可让多个表因为关联而结合成为一个表。当表的关联产生时，若其中一个表的一个字段与另一个表产生关联， 
但是这个字段不是主键，则此非主键的字段就是另一个表的外部键。

关联的类型: 
1. one to one 
2. one to many 
3. many to many(难以维护，不建议使用)

集合运算与关系运算: 
> 关系型数据库的基础是集合理论的数学理论。

关系型数据库所使用的运算共有8种，4种以传统的集合理论为基础的集合运算，4种以新的关系代数理论为基础的关系运算: 
1. 集合运算: 
  * 并集(union) 
  * 差集(difference) 
  * 交集(intersection) 
  * 笛卡尔积(cartesian product): 两个集合的"乘积" 
2. 关系运算: 
  * 选取(select): 从表中将与设置条件相符合的行(row)选取出来 
  * 投影(project): 从表中将与设置条件相符合的字段(field)选取出来 
  * 结合(join): 以共同的字段为中介，让多个表结合起来，并建立一个新表的运算。join会先执行笛卡尔积运算， 然后再对运算结果执行选取运算以缩小范围。 
  * 除法(division): 选取两个表中具有相同数据的字段，在被除的表中删除除的表中的相同数据，得到新的表。

### 2. 范化(nomolization) 
> 为了正确地执行运算且非常有效率地操作数据，必须按照特定的规则对原先复杂的表进行分割，这种对表进行分割的步骤，就是范化。 
> 一个完成范化后的表，称为范式(normal form)。

范式按照其分割的程度，可分为第一到第九阶段的范式(通常，将表分割至第三范式就可以执行正确的运算):
* 第一范式(first normal form, 1nf):<br /> 
没有任何重复出现的字段。一个表格中的所有重复的字段，都应该切割放置在不同的表中。
* 第二范式(second normal form, 2nf):<br />
当主键字段的数值确定之后，其他字段的数值便可以确定。若表中有部分相关的字段数据，应当分离出来成为独立的表。
* 第三范式(third normal form, 3nf)<br /> 所有字段的数值除了遵循主要关键字段值外，不能再遵循其他字段的值来确定。
* bc范式(boyce-codd normal form, bcnf)
* 第四范式(fourth normal form, 4nf)
* jp范式(join projection normal form, jpnf)
* 强化jp范式(strong join projection normal form, jpnf)
* 过度强化jp范式(over-strong join projection normal form, osjpnf)
* dk范式(domain key normal form, dknf)

### 3. sql概论与mysql数据库的创建
sql语言的功能分类:
1. 数据定义语言(ddl):
  * create: 建立数据库或表
  * alter: 更改数据库或表的结构
  * drop: 删除数据库或表
2. 数据操作语言(dml):
  * select: 搜索数据
  * insert: 增加数据
  * update: 更新数据
  * delete: 删除数据
3. 数据控制语言(dcl):
  * grant: 授予用户操作数据的权限
  * revoke: 撤销用户操作数据的权限
  * commit: 确定数据的更改
  * rollback: 取消数据的更改

数据库的建立、使用、删除:
```
create database <数据库名>;

use <数据库名>;

drop database <数据库名>;
```

表的建立、使用:
```
create table <表名称> (
<字段名称1> <数据类型> [not null],
<字段名称2> <数据类型> [not null],
<字段名称3> <数据类型> [not null],...);

use <表名称>;
```

数据的增加、搜索、显示:
```
insert into <表名称> (<字段名称1><字段名称2>,...) values (<值1>, <值2>,...);

select <字段名称> from <表名称>;

show <字段名称> from <表名称>;
```

### 4. mysql的数据类型

#### 数据类型一: 数值数据
数值数据的类型和范围

| 类型名称      | 内存空间    | 数值范围                                | 无符号数(unsigned)
| ------------- | ----------- | --------------------------------------- |
| tinyint       | 1b          | -128 ~ 127                              | 0 ～ 255
| smallint      | 2b          | -32768 ~ 32767                          | 0 ~ 65535
| mediumint     | 3b          | -8388608 ~ 8388607                      | 0 ~ 16777215
| int或integer  | 4b          | -2147483648 ~ 2147483647                | 0 ~ 4294967295
| bigint        | 8b          | |
| float(m,d)    | 8b          | |
| float         | 4b          | |
| double(m,d)   | m单位+2     | |
| decimal(m,d)  | m单位+2     | |

1. 使用数值数据类型的注意事项: 处理数值类型的数据速度是最快的。
  * 整数部分: 当两个操作数都是bigint时，在进行加法减法乘法时会全部用bigint来运算，因此如果运算结果大于
  bigint的无符号数所能存储数值的上限，将会发生不可预期的错误。
  * 浮点数部分: 在mysql中，浮点数又可以分为近似与精确两种，近似的浮点数占用比较小的内存，但是当存于其中的
  数据小于某个程度时就会产生误差，精确的浮点数占用比较大的内存，它使用字符来存储浮点数，因此不会有误差。
  (m,d)表示数据的长度，m表示该类型的最大长度，d表示其小数的位数。
2. 其他重要事项
  1. 使用合适的数据类型
  2. 使用数值类型字段作为主键值(primary key)，此做法可以使数据具有唯一性，也可加快存取数据的速度。
  3. 如果不确定要存入的数值有多大，可以将字段设置为decimal，这种类型的字段可以因所存入的数据大小二调整，
  并可有效保证数据的准确性
  4. 注意数据转换

##### 数据字段修饰符
1. 自动增量设置
数值类型的字段采用自动增量(auto_increment)设置时，每增加一笔新纪录，该字段的值就会自动加1。
设置为auto_increment的数值字段，其中的字段值一旦使用过，就不会再重复使用。
```
create table <表名称> (<字段名称1> int not null auto_increment primary key);
```
2. 自动补齐0修饰符
如果将一个数值类型字段设置为zerofill，则会在数值之前补齐0。例如，将1234存入一个声明为int(6)zerofill的字段时，
此时存放的数据会变成001234。
3. 无符号数
只有正数，仅适合用于整数类型字段

#### 数据类型二: 字符或字符串
字符串由字符所组成，除了要声明类型之外，还要声明所存放数据的长度大小。

字符串类型

| 类型名称      | 最大长度      | 存储空间 
| --------      | ------------- | -------- 
| char(x)       | 255b          | xb
| varchar(x)    | 255b          | x+1b 
| tinytext      | 255b          | x+1b
| text          | 65535b        | x+2b
| mediumtext    | 16777215b     | x+3b
| longtext      | 4294967295b   | x+4b
| tinyblob      | 255b          | x+1b
| bolb          | 65535b        | x+2b
| mediumblob    | 16777215b     | x+3b
| longblob      | 4294967295b   | x+4b

1. char及varchar类型

* varchar: 灵活地根据该数据的长度配置存储空间
* char: 固定长度，数据存取速度快
> mysql中同一个表不可以同时存在char及varchar这两种类型，若存在，则默认转换为varchar。但当字段长度声明小于4时，
所有的varchar字段都会转换为char类型。

2. text及blob类型
> text及blob(binary large object)这两种字段通常用来存放声音、图像及大量文字(例如存储网页表页中的文字)，
> 尤其是存储长度不固定的数据，因此这两种字段都属于变动长度且存放大量数据类型。

* text: 视大小写为相同
* blob: 视大小写为不相同
缺点: 存取这些字段数据的性能差、浪费磁盘空间。

#### 数据类型三: 其他类型
1. enum类型(多选一)
```
create table test (
field1 enum('am', 'pm' default 'am', 
field2 enum('g', 'b', 'r') default 'g' );
```
2. set类型(多选一)
声明为set类型的字段的值，只能是列表中所列出的数据。enum只能存储列表中的一个项目，而set可以存放表中的多个项目，
它适用于网页表单可选择多个项目存储的场合(不使用",")。

#### 字段修饰符
各种修饰符及其所适用的字段数据类型

| 修饰符名称        | 适用字段数据类型 
| ----------------- | ---------------- 
| auto_increment    | 所有int类型
| binary            | char, varchar 
| default           | 除了blob及text以外的所有类型
| not null          | 所有数据类型
| null              | 所有数据类型
| primary key       | 所有数据类型
| unique            | 所有数据类型
| unsigned          | 所有数据类型
| zerofill          | 所有数据类型

### 5. 建立表与索引

#### create的参数与命名

create table语句还有一个参数: if not exists，加上此参数后，当要建立的表不存在时才会建立表:
```
create table if not exists table_name(column_name, date_type);
```
命名方式的原则:
* 表名称用复数，字段名称用单数。
* 复合名称(compound name)两个名称之间用下划线连接的命名方式。
* 数据库对象名称中不可以出现空格及"-"。
* 所有数据库对象名称尽量前后保持一致。

#### 更改现有表的相关信息
使用alter table来更正或修改表的名称、字段名称、字段数据类型及相关的设置。
1. 更改表名称
```
alter table customers rename customer_table;
```
2. 更改字段名称
```
alter table customer_table change name customers_name varchar(10);
```
3. 更改字段类型
```
alter table customer_table change customers_name customers_name varchar(50);
```

#### 删除/增加表及字段

1. 删除表及字段
```
drop table table_name;
```
```
alter table table_name drop field_name;
```
上述删除操作，mysql在删除之前都没有任何提示或警告信息，所以一旦执行之后，就只能从备份的
交易日志文件(backup transaction log)中恢复。

2. 增加字段
```
alter table table_name add column_name data_type;
```

#### 使用数据库索引

索引(index)是一个可以加快存取数据库速度的结构化文件。索引可加快select语句的速度，但会降低insert、
update及delete的速度。
1. 决定索引中要包含的字段
> 选择sql语句或语句的where子句常出现的字段作为索引，或者以值不会重复的字段(通常是表的key)来建立索引。

2. 建立索引
> 默认情况下，mysql自动为表中的primary key(主键值)建立索引

```
create index index_name on table_name(column_name);
```
```
alter table table_name add index(index_name);
```

3. 删除索引
```
drop index index_name on table_name; 
```
```
alter table table_name drop index index_name;
```

### 6. 数据库的增加/删除操作

#### insert----插入数据

```
insert into [low_priority|delayed]table_name values(value1, value2, value3);
insert into table_name (field1, field2) values(value1, value2);
```
low_priority: 如果还有其他的客户端程序连接到mysql服务器上时，会等待所有的连接终止后，才会执行这里的insert命令。

强制插入数据，可使用replace。

#### update----更新数据

```
update [low_priority] table_name set column_name = value where ... ;
```

#### delete----删除数据

```
delete [low_priority] from table_name where ... limit 1;
```
limit 1: 一次只删除一笔数据。

### 7. 表达式与相关函数

#### 算术运算符及常用数学函数
算术运算符包括加法(+)、减法(-)、乘法(*)、除法(/)4种运算符。

常用的运算符或函数

| 运算符或函数 | 意义与说明
| ------------ | :---------
| +-*/         | 加法、减法、乘法、除法
| abs(x)       | 求x的绝对值
| sign(x)      | 当x为正数时返回1，为0时返回0，为负数时返回-1
| mod(n,m)     | 求n/m后的余数
| floor(x)     | 求不大于x的最大整数
| ceiling(x)   | 求不小于x的最小整数
| round(x)     | 求x的四舍五入到个位的值
| round(x,d)   | 求x的四舍五入到第d位的值 
| exp(x)       | 求对数e的x次方
| log(x)       | 求lnx的值
| log10(x)     | 求以10为底的logx值
| pow(x,y) power(x,y) | 求x的y次方
| sqrt(x)      | 求x的平方
| pi()         | 求圆周率
| sin(x),cos(x),tan(x) | 三角函数
| asin(x),acos(x),atan(x) | 反三角函数
| atan2(x,y)   | 求y/n的arctan函数值
| cot(x)       | 
| rand(n)      | 产生一个介于0-1.0之间的浮点随机数
| least(n1,n2,n3...) | 返回最小值
| greatest(n1,n2,n3...) | 返回最大值
| degrees(x)   | 将弧度转成角度
| radians(x)   | 将角度转成弧度
| truncate(x,d)| 将x的小数以下的第d位以后无条件舍去

#### 比较运算符
在where子句中最重要的就是比较运算符，常用的比较运算符有: =、>与>=、<与<=、<>(!=)

| 运算符或函数 | 意义或说明 
| ------------ | ---------- 
| is null      | 检查字段的值是否为null。null值不是0，也不是空格字符，也不是长度为0的字符串，当一个字段处于没有存储任何值的状态时，就称为存储着null值
| is not null  | 
| between a and b | 指定一个介于A与B之间的范围
| in(value1,value2...) | 检查是否符合列举项目的范围
| not in(value1,value2) | 检查是否不符合列举项目的范围
| is null(exp) | 检查exp的表达式中是否为NULL值
| colesce(list) | 在list的所有项目中，找出第一个不是NULL值的数据
| interval(n,n1,n2...) | 当n<n1时，返回1，否则返回0；当n<n2时，再将返回值加1，否则返回0，依次类推

#### 逻辑运算符
AND, OR, NOT, LIKE 

通配符: 
* %或*: 0个以上字符
* _或?: 一个字符 

#### 字符串函数
算术运算符是以数字为操作对象，而字符串函数则是字符串类型作为操作的目标。

| 字符串函数 | 意义及说明 
| ---------- | ---------- 
| ascii(x)   | 返回x字符串中最左边字符的ascii值
| conv(n,from_base,to_base) | 将n的底from_base转成to_base，n可以是字符串或数字
| bin(n)     | 将十进制的数值n转换为二进制
| oct(n)     | 将十进制的数值n转换为八进制
| hex(n)     | 将十进制的数值n转换为十六进制
| char(n,...) | 将ascii字符n转换为对应的字符
| concat(s1,s2...) | 将字符串相连接
| length(s)  | 返回字符串的长度
| locate(s1,s2) | 返回s1在s2中第一次出现的位置

### 数据库的查询操作

1. order by 
  1. 字段名称
  2. unsigned integer 
  3. asc/desc: 递增/递减
  4. distinct: 删除重复

2. group by 
指定一个字段，然后根据此字段将数据分成数个组，分成组后方便对各个组的数据进行计算，计算时经常会用到统计函数。

统计函数及其说明
* count(expr): 计数
* avg(expr): 求平均值
* sum(expr): 求总和
* min(expr): 求最小值
* max(expr): 求最大值
* std(expr): 求标准差

3. having

### 数据的导入与导出
导入: mysqlimport
到处: mysqldump

