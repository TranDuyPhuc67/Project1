<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="text-info">Sửa món ăn</h3>
<form action="FoodServlet?action=edit" method="post" class="mt-3">
    <div class="mb-3">
        <label for="foodid" class="form-label">Mã món ăn</label>
        <input type="text" class="form-control" id="foodid" name="foodid" value="${food.foodId}" readonly>
    </div>
    <div class="mb-3">
        <label for="foodname" class="form-label">Tên món ăn</label>
        <input type="text" class="form-control" id="foodname" name="foodname" value="${food.foodName}" required>
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Giá</label>
        <input type="number" class="form-control" id="price" name="price" value="${food.price}" min="0" required>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Mô tả</label>
        <textarea class="form-control" id="description" name="description" rows="3">${food.description}</textarea>
    </div>
    <div class="mb-3">
        <label for="picture" class="form-label">Ảnh</label>
        <input type="text" class="form-control" id="picture" name="picture" value="${food.picture}">
    </div>
    <div class="form-check mb-3">
        <input type="checkbox" class="form-check-input" id="active" name="active" 
            <c:if test="${food.isActive()}">checked</c:if>>
        <label class="form-check-label" for="active">Còn hàng</label>
    </div>
    <button type="submit" class="btn btn-info">Lưu thay đổi</button>
</form>
