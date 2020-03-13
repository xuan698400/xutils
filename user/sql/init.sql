# 本地安装目录：/usr/local/mysql/bin
# 执行：./mysql -u root -p
# 密码：123456


# 创建数据库
CREATE SCHEMA `bpmweb` DEFAULT CHARACTER SET utf8;

# 创建用户并授权
CREATE USER `bpmweb`@`%` IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON `bpmweb`.* to `bpmweb`@`%` identified by '123456';
flush PRIVILEGES;