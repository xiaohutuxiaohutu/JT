<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>欢迎注册EasyMall</title>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${app}/css/regist.css"/>
	<script type="text/javascript" src="${app}/js/ajax.js"></script>
	<script type="text/javascript">
		/* 注册表单校验 */
		function checkForm(){
			var flag = true;
			//>>非空校验
			flag = checkNull("username", "用户名不能为空") && flag;
			flag = checkNull("password", "密码不能为空") && flag;
			flag = checkNull("password2", "确认密码不能为空") && flag;
			//>>两次密码是否一致校验
			flag = checkPassword("password", "两次密码不一致") && flag;
			flag = checkNull("nickname", "昵称不能为空") && flag;
			flag = checkNull("email", "邮箱不能为空") && flag;
			//>>邮箱格式是否正确
			flag = checkEmail("email", "邮箱格式不正确") && flag;
			
			flag = checkNull("valistr", "验证码不能为空") && flag;
			
			
			return flag;
		}
		
		/* 检查邮箱格式 */
		function checkEmail (name, msg){
			var email = document.getElementsByName(name)[0].value;
			setMsg(name,"");
			checkNull(name, "邮箱不能为空");
			var reg = /^\w+@\w+(\.\w+)+$/;
			if(email != "" && !reg.test(email)){
				setMsg(name, msg);
				return false;
			}
			return true;
		}
		
		/* 检查两次密码是否一致 */
		function checkPassword(name, msg){
			var psw1 = document.getElementsByName(name)[0].value;
			var psw2 = document.getElementsByName(name+"2")[0].value;
			setMsg(name+"2","");
			checkNull(name+"2", "确认密码不能为空");
			if(psw1 != "" && psw2 != ""){
				if(psw1 != psw2){
					setMsg(name+"2", msg);
					return false;
				}
			}
			return true;
		}
		
		/* 检查表单项是否为空 */
		function checkNull(name, msg){
			var value = document.getElementsByName(name)[0].value;
			setMsg(name,"");
			if(value == ""){
				setMsg(name,msg);
				return false;
			}
			return true;
		}
		
		/* 设置提示消息 */
		function setMsg(name, msg){
			document.getElementById(name+"_msg").innerHTML = "<font style='color:red;font-size: 14px;'>"+msg+"</font>";
		}
		
		/* 利用ajax实现用户名是否存在的校验 */
		function checkUsername(thisobj){
			//检查用户名是否为空
			if(!checkNull("username", "用户名不能为空")){
				return;
			}
			
			//利用ajax实现用户名是否存在的校验
			var username = thisobj.value;
			
			//1.获取XMLHttpRequest对象
			var xmlHttp = ajaxFunction();
			//2.建立连接
			xmlHttp.open("POST", "${app}/AjaxCheckUsernameServlet", true);
			//3.发送请求
			//通知服务器发送的数据是请求参数
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttp.send("username="+username);
			//4.注册监听
			xmlHttp.onreadystatechange = function(){
				//时刻监听服务器处理请求的过程(状态), 但是我们只关心为4的状态
				if(xmlHttp.readyState == 4){
					//如果请求处理成功了才获取响应结果
					if(xmlHttp.status == 200){
						//获取响应结果
						var result = xmlHttp.responseText;
						if(result == "true"){
							setMsg("username", "用户名已存在");
						}else{
							setMsg("username", "恭喜, 用户名可以使用");
						}
					}
				}
			}
		}
		
	
	</script>
</head>
<body>
	<h1>欢迎注册EasyMall</h1>
	<form action="${app}/RegistServlet" method="POST" onsubmit="return checkForm()">
		<table>
			<tr>
				<td class="tds" colspan="2" style="color:red;font-size:15px;text-align:center;">
					${ msg }
				</td>
			</tr>
			<tr>
				<td class="tds">用户名：</td>
				<td><input type="text" name="username" value="${ param.username }"
						onblur="checkUsername(this)"
					/>
					<span id="username_msg"></span>
				</td>
			</tr>
			<tr>
				<td class="tds">密码：</td>
				<td><input type="password" name="password" onblur="checkNull('password', '密码不能为空')"/>
					<span id="password_msg" ></span>
				</td>
			</tr>
			<tr>
				<td class="tds">确认密码：</td>
				<td><input type="password" name="password2"  onblur="checkPassword('password', '两次密码不一致')"/>
					<span id="password2_msg" ></span>
				</td>
			</tr>
			<tr>
				<td class="tds">昵称：</td>
				<td><input type="text" name="nickname" value="${ param.nickname }"
						 onblur="checkNull('nickname', '昵称不能为空')"
					/>
					<span id="nickname_msg" ></span>
				</td>
			</tr>
			<tr>
				<td class="tds">邮箱：</td>
				<td><input type="text" name="email" value="${ param.email }"
						 onblur="checkEmail('email', '邮箱格式不正确')"
					/>
					<span id="email_msg" ></span>
				</td>
			</tr>
			<tr>
				<td class="tds">验证码：</td>
				<td><input type="text" name="valistr" onblur="checkNull('valistr', '验证码不能为空')"/>
					<img id="yzm_img" src="${app}/ValiImageServlet" style="cursor: pointer" onclick="changeImage(this)"/>
					<span id="valistr_msg" ></span>
				</td>
			</tr>
			<script>
				function changeImage(thisobj){
					thisobj.src = "${app}/ValiImageServlet?time="+new Date().getTime();
				}
			</script>
			<tr>
				<td colspan="2">
					<input type="submit" value="注册用户"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
body {
	background: #F5F5F5;
	text-align: center;
}

table {
	text-align: center;
	margin: 0px auto;
}

th {
	background-color: silver;
}
</style>
</head>
<body>
	<h1>销售榜单</h1>
	<a href="#">销售榜单下载</a>
	<hr>

	<table bordercolor="black" border="1" width="95%" cellspacing="0px" cellpadding="5px">
		<tr>
			<th>商品id</th>
			<th>商品名称</th>
			<th>销售总量</th>
		</tr>
			<tr>
				<td>1</td>
				<td>测试商品</td>
				<td>500</td>
			</tr>
	</table>
</body>
</html>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        