

在 MySQL 中，权限是用于控制用户可以对数据库或数据库对象（如表、视图、存储过程等）执行哪些操作的。MySQL 提供了一系列可授予的权限，从全局级别（对整个服务器）到具体数据库和表级别。下面列出了常见的 MySQL 
权限以及它们的用途。

## 全局权限
这些权限适用于整个 MySQL 服务器，可以通过授予用户对 *.* 的权限来实现。
* ALL PRIVILEGES: 赋予用户所有可用的权限。这是最高权限，慎用。
* CREATE USER: 允许创建新用户账户。
* DROP: 允许删除数据库、表、视图等。
* GRANT OPTION: 允许用户将他们拥有的权限授予其他用户。
* RELOAD: 允许执行 FLUSH 操作，例如 FLUSH PRIVILEGES，刷新 MySQL 的缓存。
* SHUTDOWN: 允许关闭 MySQL 服务器。
* SUPER: 允许执行管理操作，如 KILL 查询、全局日志、主从复制控制等。
* FILE: 允许在服务器文件系统中读取和写入文件。
* PROCESS: 允许查看其他用户的线程信息（通过 SHOW PROCESSLIST）。
* SHOW DATABASES: 允许查看所有数据库的列表（而不仅仅是用户有权限访问的数据库）。
* REPLICATION SLAVE: 允许在主从复制中配置该用户作为从库的用户。
* REPLICATION CLIENT: 允许该用户查询主从复制相关的状态信息。
* LOCK TABLES: 允许使用 LOCK TABLES 语句锁定表。

## 数据库级别权限
这些权限针对特定的数据库，可以通过授予用户对 database_name.* 的权限来实现。
* CREATE: 允许在数据库中创建新的表、视图等对象。
* ALTER: 允许修改现有的表或视图（例如增加、修改、删除列）。
* DROP: 允许删除数据库内的表、视图等对象。
* DELETE: 允许删除表中的记录。
* INSERT: 允许在表中插入新的记录。
* SELECT: 允许查询表中的数据。
* UPDATE: 允许更新表中的现有记录。
* INDEX: 允许创建或删除表的索引。
* EXECUTE: 允许执行存储过程和函数。
* REFERENCES: 允许在外键约束中引用其他表。
* CREATE VIEW: 允许创建新的视图。
* SHOW VIEW: 允许查看视图的定义。
* CREATE ROUTINE: 允许创建存储过程和函数。
* ALTER ROUTINE: 允许修改或删除存储过程和函数。
* TRIGGER: 允许创建、删除和触发触发器。
* EVENT: 允许创建和管理调度事件。



## 表级别权限
这些权限针对特定的表，可以通过授予用户对 database_name.table_name 的权限来实现。
* SELECT: 允许查询表中的数据。
* INSERT: 允许在表中插入新的记录。
* UPDATE: 允许更新表中的现有记录。
* DELETE: 允许删除表中的记录。
* ALTER: 允许修改表结构，例如增加或删除列。
* DROP: 允许删除表。
* INDEX: 允许创建或删除表的索引。
* REFERENCES: 允许在外键约束中引用该表。
* TRIGGER: 允许创建、删除和触发表的触发器。


## 列级别权限
这些权限针对特定表中的特定列，可以通过授予用户对 database_name.table_name(column_name) 的权限来实现。
* SELECT: 允许查询表中的特定列数据。
* INSERT: 允许在表中的特定列插入数据。
* UPDATE: 允许更新表中的特定列数据。


## 存储过程和函数权限
这些权限专门用于管理存储过程和函数。
* EXECUTE: 允许执行存储过程和函数。
* ALTER ROUTINE: 允许修改或删除存储过程和函数。

## 视图权限
针对视图的权限，类似于表级权限。
* CREATE VIEW: 允许创建新的视图。
* SHOW VIEW: 允许查看视图的定义。

## 事件权限
* EVENT: 允许创建和管理调度事件。

## 触发器权限
* TRIGGER: 允许创建、删除和触发触发器。


## 总结
MySQL 提供了丰富的权限系统，允许细粒度地控制用户对数据库、表、视图、存储过程等对象的操作能力。根据实际需求，可以授予用户全局级别、数据库级别、表级别或列级别的权限。使用这些权限时，应当根据最小权限原则授予用户最少的必要权限，以确保系统安全性。









# MySQL权限及使用示例

## 全局权限

全局权限适用于整个 MySQL 服务器，可以通过授予用户对 `*.*` 的权限来实现。这些权限通常与服务器管理和用户管理操作有关。

### `ALL PRIVILEGES`

- **说明**: 赋予用户所有可用的权限。这是最高权限，通常只授予管理员。
- **示例**: 
    ```sql
    GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
    ```
    这将使 `admin` 用户在 `localhost` 主机上拥有对所有数据库和表的完全控制权。

### `CREATE USER`

- **说明**: 允许创建新用户账户。
- **示例**: 
    ```sql
    GRANT CREATE USER ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户创建新用户账户。

### `DROP`

- **说明**: 允许删除数据库、表、视图等。
- **示例**: 
    ```sql
    GRANT DROP ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户删除任何数据库或表。

### `GRANT OPTION`

- **说明**: 允许用户将他们拥有的权限授予其他用户。
- **示例**: 
    ```sql
    GRANT ALL PRIVILEGES ON mydb.* TO 'alice'@'localhost' WITH GRANT OPTION;
    ```
    这允许 `alice` 用户不仅能管理 `mydb` 数据库，还能将这些权限授予其他用户。

### `RELOAD`

- **说明**: 允许执行 `FLUSH` 操作，例如 `FLUSH PRIVILEGES`，刷新 MySQL 的缓存。
- **示例**: 
    ```sql
    GRANT RELOAD ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户执行诸如 `FLUSH PRIVILEGES` 的操作来刷新权限表。

### `SHUTDOWN`

- **说明**: 允许关闭 MySQL 服务器。
- **示例**: 
    ```sql
    GRANT SHUTDOWN ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户关闭 MySQL 服务器。

### `SUPER`

- **说明**: 允许执行管理操作，如 `KILL` 查询、设置全局变量、启动/停止复制等。
- **示例**: 
    ```sql
    GRANT SUPER ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户执行高级管理任务，比如终止其他用户的查询或更改全局系统变量。

### `FILE`

- **说明**: 允许用户在服务器文件系统中读取和写入文件。
- **示例**: 
    ```sql
    GRANT FILE ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户导入或导出数据文件，例如使用 `LOAD DATA INFILE` 或 `SELECT INTO OUTFILE`。

### `PROCESS`

- **说明**: 允许查看其他用户的线程信息（通过 `SHOW PROCESSLIST`）。
- **示例**: 
    ```sql
    GRANT PROCESS ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户查看服务器中正在运行的所有查询。

### `SHOW DATABASES`

- **说明**: 允许查看所有数据库的列表。
- **示例**: 
    ```sql
    GRANT SHOW DATABASES ON *.* TO 'admin'@'localhost';
    ```
    这允许 `admin` 用户查看所有数据库，而不仅仅是他们有权限访问的数据库。

### `REPLICATION SLAVE`

- **说明**: 允许在主从复制中配置该用户作为从库的用户。
- **示例**: 
    ```sql
    GRANT REPLICATION SLAVE ON *.* TO 'replica'@'localhost';
    ```
    这允许 `replica` 用户作为从库连接到主库。

### `REPLICATION CLIENT`

- **说明**: 允许该用户查询主从复制相关的状态信息。
- **示例**: 
    ```sql
    GRANT REPLICATION CLIENT ON *.* TO 'monitor'@'localhost';
    ```
    这允许 `monitor` 用户查看复制状态。

### `LOCK TABLES`

- **说明**: 允许使用 `LOCK TABLES` 语句锁定表。
- **示例**: 
    ```sql
    GRANT LOCK TABLES ON *.* TO 'backup'@'localhost';
    ```
    这允许 `backup` 用户在执行备份时锁定表。

## 数据库级别权限

这些权限针对特定的数据库，可以通过授予用户对 `database_name.*` 的权限来实现。这些权限适用于管理数据库中的对象，例如表、视图、存储过程等。

### `CREATE`

- **说明**: 允许在数据库中创建新的表、视图、存储过程等对象。
- **示例**: 
    ```sql
    GRANT CREATE ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中创建新的表或视图。

### `ALTER`

- **说明**: 允许修改现有的表或视图（例如增加、修改、删除列）。
- **示例**: 
    ```sql
    GRANT ALTER ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中修改表结构。

### `DROP`

- **说明**: 允许删除数据库内的表、视图等对象。
- **示例**: 
    ```sql
    GRANT DROP ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户删除 `mydb` 数据库中的表或视图。

### `DELETE`

- **说明**: 允许删除表中的记录。
- **示例**: 
    ```sql
    GRANT DELETE ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户删除 `mydb` 数据库中的记录。

### `INSERT`

- **说明**: 允许在表中插入新的记录。
- **示例**: 
    ```sql
    GRANT INSERT ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中的表插入新数据。

### `SELECT`

- **说明**: 允许查询表中的数据。
- **示例**: 
    ```sql
    GRANT SELECT ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户查询 `mydb` 数据库中的表数据。

### `UPDATE`

- **说明**: 允许更新表中的现有记录。
- **示例**: 
    ```sql
    GRANT UPDATE ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户更新 `mydb` 数据库中的表数据。

### `INDEX`

- **说明**: 允许创建或删除表的索引。
- **示例**: 
    ```sql
    GRANT INDEX ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中的表上创建或删除索引。

### `EXECUTE`

- **说明**: 允许执行存储过程和函数。
- **示例**: 
    ```sql
    GRANT EXECUTE ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户执行 `mydb` 数据库中的存储过程和函数。

### `REFERENCES`

- **说明**: 允许在外键约束中引用其他表。
- **示例**: 
    ```sql
    GRANT REFERENCES ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中使用外键引用。

### `CREATE VIEW`

- **说明**: 允许创建新的视图。
- **示例**: 
    ```sql
    GRANT CREATE VIEW ON mydb.* TO 'developer'@'localhost';
    ```
    这允许 `developer` 用户在 `mydb` 数据库中创建视图。

