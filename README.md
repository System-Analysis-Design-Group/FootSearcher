# FootSearch

# 项目构建顺序

## 构建 Mysql 项目
1. 下载 mysql zip 文件包

https://dev.mysql.com/downloads/mysql/

2. 安装启动 mysql，并创建一个数据库
3. 设置mysql 用户密码和数据库名到 `/main/resources/application.properties` 中
4. 第一次启动 Application.java 后，将 `/main/resources/application.properties` 中的 `spring.jpa.hibernate.ddl-auto` 改为 `update`


## 构建 mongoDB 项目

1. 设置IP 和 Port 到 `/main/resources/application.properties` 中
2. 设置  Application.java 中的 `@SpringBootApplication(exclude = {ShiroConfiguration.class})` 从而关闭 Mysql 的启动请求

## jar 打包流程
1. maven compile
2. maven package