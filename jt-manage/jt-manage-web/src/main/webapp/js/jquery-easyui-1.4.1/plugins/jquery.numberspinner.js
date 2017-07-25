<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
	<style type="text/css">
		body{
			background: #F5F5F5;
		}
		h1{
			text-align: center;
		}
		table{
			margin: 0px auto;
		}
  	</style>
  </head>
  <body>
	<h1>EasyMall添加商品</h1>
	<hr>
	<form action="${app}/servlet/BackProdAddServlet"
	   enctype="multipart/form-data" method="POST">
	   <input type="hidden" name="id" value="${prod.id }"/>
		<table border="1" cellspacing="0" cellpadding="3" >
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${prod.name }"/></td>
			</tr>
			<tr>
				<td>商品单价</td>
				<td><input type="text" name="price" value="${prod.price }"/></td>
			</tr>
			<tr>
				<td>商品种类</td>
				<td>
					<select name="category">
						<option value="电子数码" 
						<c:if test="${prod.category=='电子数码'}">selected="selected"</c:if>
						>电子数码</option>
						<option value="图书杂志"
						<c:if test="${prod.category=='图书杂志'}">selected="selected"</c:if>>图书杂志</option>
						<option value="床上用品">床上用品</option>
						<option value="家用电器">家用电器</option>
						<option value="日用百货">日用百货</option>
						<option value="服装服饰">服装服饰</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>库存数量</td>
				<td><input type="text" name="pnum" value="${prod.pnum }"/></td>
			</tr>
			<tr>
				<t