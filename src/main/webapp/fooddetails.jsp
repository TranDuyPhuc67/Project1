<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h4>Chi tiết món ăn</h4>
<div class="row" style="border: 1px gray solid;border-radius:5px;">
	<div class="col-3">
		<img src="images/${food.picture}" width="95%" />
	</div>
	<div class="col-9">
		<p>${food.foodName}</p>
		<p>
			 Giá:
			<f:formatNumber value="${food.price}" />
			đ
		
	</div>
	<div class="col-12">
		<h4>Mô tả</h4>
		<p>${food.description}</p>
	</div>
</div>