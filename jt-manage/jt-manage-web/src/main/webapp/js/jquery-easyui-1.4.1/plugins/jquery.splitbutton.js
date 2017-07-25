一.Response对象
	代表http响应的对象
	1.response继承结构
		ServletResponse -- 通用的响应接口, 提供了一个response对象应该具有的功能
			|
			|--HttpServletResponse 继承了ServletResponse接口, 并且添加了很多和Http协议相关的方法

	2.response对象上提供的方法
		状态行: HTTP/1.1 200 ok
		若干响应头: 
			xxx: xxx
			xxx: xxx
			xxx: xxx
		响应实体内容: 
			xxxxx

		设置状态码的方法:
			void setStatus(int sc)
			void setStatus(int sc, String sm) 

		设置响应头的方法:
			void setHeader(String name, String value) 
			void setDateHeader(String name, long date) 
			void setIntHeader(String name, int value)  

			void addHeader(String name, String value) 
			void addDateHeader(String name, long date) 
			void addIntHeader(String name, int value) 

		设置响应实体内容:
			getOutputStream()
			getWriter()

	3.response对象功能
		(1) 向客户端发送数据
			getWriter()
			getOutputStream()

			**字节流发送数据时的乱码问题
				如果在服务器端发送数据时, 指定了使用utf-8来发送数据, 同时也要通知浏览器使用相同的码表来接受数据, 才不会出现乱码问题!

				//发送数据时指定使用utf-8
				byte[] bytes = "中国".getBytes("utf-8");
				response.getOutputStream().write(bytes);

				//同时通知浏览器使用utf-8来接受数据
				response.setHeader