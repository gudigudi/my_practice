# 索引

*类型*
* 单列索引
* 复合索引
* 唯一索引
* 主键索引

## 单列索引
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

## 复合索引
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

## 唯一索引

## 主键索引
