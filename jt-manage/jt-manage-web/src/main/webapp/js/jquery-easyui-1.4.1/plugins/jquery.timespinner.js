跳转
			*请求重定向:
				两次请求, 两次响应, 地址栏地址会发生变化
				request对象不是同一个
				请求重定向既可以是不同的WEB应用或者是不同的服务器之间进行资源的跳转, 也可以是同一个服务器或者同一个WEB应用之间进行资源的跳转.

			*定时刷新:
				两次请求, 两次响应, 地址栏地址会发生变化
				request对象不是同一个
				定时刷新既可以是不同的WEB应用或者是不同的服务器之间进行资源的跳转, 也可以是同一个服务器或者同一个WEB应用之间进行资源的跳转.

				定时刷新和重定向的区别在于: 重定向是立即跳转, 而定时刷新是指定多少秒之后才进行跳转(如果时间为0, 定时刷新和重新功能效果一样), 在跳转之前, 服务器可以向浏览器发送文本数据并维系一段时间.
		那什么时候用哪种方式进行资源的跳转呢?
			**如果是同一服务器中的同一应用内部的资源跳转:
				~如果需要利用request域在跳转的资源之间传输数据, 只能用请求转发
				~如果不想让地址栏发生变化, 只能用请求转发
				~如果需要地址栏发生变化, 只能用重定向或定时刷新
				~如果没有什么特殊需求, 三种方式都可以, 但是推荐使用转发, 可以减少请求次数降低服务器的压力.
				~如果只是想更新刷新操作, 最好使用重定向或定时刷新, 使用请求转发, 在刷新时会把刚才的操作再做一遍, 可能会导致一些问题, 比如表单重复提交或重复支付订单等

			**如果是不同服务器或不同的WEB应用内部的资源跳转, 只能用重定向或者是定时刷新:
				重定向和定时刷新的主要区别在于: 重定向会立即跳转, 而定时刷新可以设置一个时间间隔, 在指定时间后再进行跳转. 
				如果在跳转之前需要输出提示信息(如: 注册成功, xx秒后跳转到xxx)只能用定时刷新, 否则两种方式都可以.

		(4) 控制浏览器的缓存行为
			不同的浏览器或者是相同的浏览器的不同版本或者是相同的浏览器的相同版本, 如果做了不同的配置, 浏览器的缓存行为可能是不一样. 如果靠浏览器自己去判断一个资源是否需要缓存, 是非常不靠谱的!!

			我们应该能够在服务器端来控制浏览器是否缓存一个资源:

			控制浏览器不要缓存资源:
				Expires Cache-Control Pragma
				response.setDateHeader("Expires", 1000); //1970-1-1
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Pragma", "no-cache");
			
			控制浏览器缓存资源:
				response.setDateHeader("Expires", System.currentTimeMillis()+1000*3600*24); //1970-1-1
				response.setHeader("Cache-control", "max-age=5");
						
=======================================
二.EasyMall项目 -- 注册功能实现01
	1.搭建环境
		(1) 创建EasyMall工程
		(2) 配置www.easymall.com虚拟主机, 并设置为缺省的虚拟主机
		(3) 将EasyMall工程发布到 www.easymall.com 虚拟主机中, 并实现通过www.easymall.com地址来访问EasyMall项目的主页.
			部署项目采用第二种方式: 在[tomcat]/conf/Catalina/www.easymall.com/ 目录下添加一个文件 ROOT.xml 在文件中添加如下:
				<Context docBase="D:\softspace\Workspaces\BIG_1611\EasyMall\WebRoot" />

			配置完虚拟主机需要在hosts文件中配置主机名和IP地址的映射关系

		(4) 配置文件和jar包
			mysql包
			c3p0包
			c3p0-config.xml配置文件
	2.导入静态页面
		利用jsp来展示数据, 目前可以把jsp暂时理解为可以写java代码html
		首页(_head.jsp/_foot.jsp/index.jsp )和注册页面(regist.jsp)

	3.实现注册功能
		//0.解决请求参数乱码
		
		//1.接受表单数据
		
		//2.校验数据
		
		//3.注册用户 -- 把数据保存到数据库
		
		//4.给出提示


		//设计数据库和表以及表字段
			create database easymall;
			use easymall;
			create table user(
				id int primary key auto_increment,
				username varchar(100),
				password varchar(100),
				nickname varchar(100), 
				email varchar(100)
			);

			insert into user values(null, "admin", "123", "超级管理员", "admin@163.com");
			insert into user values(null, "张飞", "123", "管理员", "feifei@163.com");
			
		

		
		

	
		

			
			
			

			
			
		
	
	
			
		

		

		
                                                                                                                     