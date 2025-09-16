<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đồ ăn nhanh</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" />
<style>
#content {
	padding: 15px;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="nav">
			<a class="nav-link active" href="FoodServlet">Trang chủ</a> <a
				class="nav-link" href="FoodServlet?action=foods">Sản phẩm</a> <a
				class="nav-link" href="CartServlet?action=viewCart">Giỏ hàng</a> <a
				class="nav-link" href="FoodServlet?action=admin">Quản trị</a> 
				<% 
				if (session.getAttribute("username") != null){
				%>
				 <span style="font-weight: bold;">Xin chào: <%= session.getAttribute("username") %></span>
            <a class="nav-link" href="LogoutServlet">Thoát</a>
				<%
				}else {
				%>
				<a class="nav-link" href="login.jsp"> Đăng nhập</a>
				<a class="nav-link" href="register.jsp">Đăng ký</a> 
				<%
				}
				%>

				
				
				
		</nav>
		<hr>
		<div id="content">
			<c:if test="${!(empty param.page)}">
				<jsp:include page="${param.page}.jsp"></jsp:include>
			</c:if>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
</body>
</html>