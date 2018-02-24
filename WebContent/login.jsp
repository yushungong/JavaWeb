<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 引入自定义标签 -->
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="smvc" value="${pageContext.request.contextPath}"/>
	<!-- <form action="loginCheck" method="post">
		用户名：<input type="text" name="userName">
		密码：<input type="password" name="password">
		<input type="submit" value="提交">
	</form> -->
	<a href="${smvc}/notFound.jsp">页面跳转</a>
	<my:login servlet="loginCheck" textName="userName" passwordName="password" />
</body>
</html>