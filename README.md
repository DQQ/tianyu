# tianyu
也叫天雨，因为接到任务时，珠海下大雨

天雨是一个提测合格率统计系统，统计所有项目提测，及质量情况

#运行环境

1，tomcat，java，servlet，mysql

#  tomcat数据连接池配置
在tomcat-6.0.20\conf目录下的context.xml文件的<Context>中加入配置代码
代码如下：
	<Resource name="jdbc/mysql" 
		   auth="Container"  
                		   type="javax.sql.DataSource" 
		   username="root"   <!-- 数据库用户名-->
		   password="root"  <!-- 登陆数据库密码-->
                 		   driverClassName="com.mysql.jdbc.Driver"  
                 		   url="jdbc:mysql://localhost:3306/myblog?autoReconnect=true" 
		   maxActive="80" 
	                     maxIdle="40" />

在tomcat-6.0.20\lib目录中加入数据库驱动mysql-connector-java-5.1.5-bin.jar

#所用技术：
（1）J2EE技术。
（2）分页技术。
（3）tomcat数据连接池技术。
（4）servlet技术。
（5）Jsp技术。
（6）Javabean技术。
（7）使用MySQL 5.1数据库。

