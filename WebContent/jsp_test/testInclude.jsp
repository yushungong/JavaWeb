<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>

<!-- 静态include -->
<!-- 用于在jsp中包含一个静态文件，可以是jsp页面、HTML页面、文本文件 -->
<%@ include file="include1.jsp" %>

<!-- 动态include -->
<!-- 当前页面和被包含的资源是相互独立的 -->
<jsp:include page="include2.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
testInclude.jsp.......
</body>
</html>