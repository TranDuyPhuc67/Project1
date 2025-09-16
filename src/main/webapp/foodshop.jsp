<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4>Đặt hàng ngay !!!</h4>
<div class="row">	<c:forEach items="${foods}" var="f">
		<div class="col-3">
			<div class="card" style="width: 240px">
				<img class="card-img-top" src="images/${f.picture}"/>
				<div class="card-body">
					<h5 class="card-title">${f.foodName}</h5>
					<p class="card-text">Giá: <fmt:formatNumber value="${f.price}" type="number" /> đ</p> 
					<a href="CartServlet?action=addToCart&foodId=${f.foodId}" class="btn btn-primary">Mua</a> <a href="FoodServlet?action=detail&foodid=${f.foodId}" class="btn btn-success">Chi tiết</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>