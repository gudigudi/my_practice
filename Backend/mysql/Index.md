# 索引

*类型*
* 单列索引
* 复合索引
* 唯一索引
* 主键索引

#### 单列索引
> 单列索引，也称为普通索引，单列索引是最基本的索引，它没有任何限制，创建一个单列索引，语法如下:

```sql
-- 直接创建索引
CREATE INDEX index_name ON table_name(column_name);
-- 修改表, 添加索引
ALTER TABLE table_name ADD INDEX index_name ON (column_name)
-- 创建表时就创建索引
CREATE TABLE `table`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY(`id`)
  index_name (name(32))
);
```

#### 复合索引
>复合索引是在多个字段上创建的索引。复合索引遵守“最左前缀”原则，即在查询条件中使用了复合索引的第一个字段，索引才会被使用。因此，在复合索引中索引列的顺序至关重要。创建一个复合索引的语法如下：

```sql
CREATE INDEX index_name ON table_name(column_name1,column_name2,column_name3);

ALTER TABLE table_name ADD INDEX index_name ON (column_name1,column_name2,column_name3);

CREATE TABLE `table`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `pinyin` varchar(32),
  PRIMARY KEY (`id`),
  index_name (name(32),pinyin(32))
);
```

#### 唯一索引
> 创建唯一索引必须指定关键字UNIQUE，唯一索引和单列索引类似，主要的区别在于，唯一索引限制列的值必须唯一，但允许有空值。对于多个字段，唯一索引规定列值的组合必须唯一。如创建username为唯一索引，那么username的值是不可以重复的，

```sql
CREATE UNIQUE INDEX index_name ON table_name(column_name);

ALTER TABLE table_name ADD UNIQUE INDEX index_name ON (column_name);

CREATE TABLE `table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE index_name(name(32))
);
```

#### 主键索引
>主键索引也称丛生索引，是一种特殊的唯一索引，不允许有空值。创建主键索引语法如下

```sql
ALTER TABLE table_name ADD PRIMARY KEY(column_name);
```

>一般情况下在创建表时，指明了主键时，主键索引就已自动创建了，因此无需我们手动创建。

```sql
CREATE TABLE `table`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY(`id`)
);
```
