# Javabeginer-ATM-SQL
>简单控制台ATM系统，使用数据库连接保存数据（MySQL）
>
控制台交互的简单ATM演示系统，主要用于数据库连接的练习。

数据保存在数据库中，程序结束后数据不会消失。

# 测试本程序前准备
> 1.在本地MYsql数据库中创建"atm"数据库;
>
> 2.确保数据库服务端口为3306;
>
> 3.在atm库中创建表格"user"格式如下：

|   id|   serialnum|name  |  password | balance  |
| ------------ | ------------ | ------------ | ------------ | ------------ |
|varchar(255)  | int,主键自增| varchar(255)  |varchar(255)   | double(255,2)  |

方法一：使用navicat界面工具创建表格。

方法二 : 运行`com.test`包下的CreateTable类的主函数创建user表。

方法三：直在数据库中运行以下代码:
```sql
CREATE TABLE IF NOT EXISTS `user` (
`id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
`serialnum`  int(255) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`balance`  double(255,2) NULL DEFAULT NULL ,
PRIMARY KEY (`serialnum`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=5
ROW_FORMAT=COMPACT
;
```
完成数据库建表后方可运行`com.test`包下的测试函数运行程序。
