# MySQL dump data 



## 小工具

首先，可以装一下小工具，可以对命令进行提示、自动补全。

`https://github.com/dbcli/mycli`



## 连接 

指定地址与端口，连接到数据库。

```sql
# 原生的命令
mysql -h host/ip -P port -u username -p
# 工具登陆
mycli -h host/ip -P port -u username
```



## 导出

### 检查账号权限

mysql 有单独的 file 权限，需要单独赋予。

具体参考文档：

- [Grant Tabels](https://dev.mysql.com/doc/mysql-security-excerpt/5.7/en/grant-tables.html)

- [Grant Syntax](https://dev.mysql.com/doc/refman/5.7/en/grant.html)
- [Privileges Provided By MySQL -- FILE](https://dev.mysql.com/doc/refman/5.7/en/privileges-provided.html#priv_file)

通过 SQL 语句查看指定用户权限：

```shell
> show grants fro username
```

通过上方的相关文档，检查账号是否具有导出、导入权限。

当具有导出权限时，可通过以下语句导出数据：

```shell
> select [***]  INTO OUTFILE target-file
```



