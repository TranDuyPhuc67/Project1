<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4>Quản trị sản phẩm</h4>
<p> ${msg} </p>
<p><a href="FoodServlet?action=add" class="btn btn-primary">Thêm mới</a></p>
<table class="table table-bordered">
	<tr>
		<th>Mã số</th>
		<th>Tên món ăn</th>
		<th>Giá</th>
		<th>Ảnh</th>
		<th>Ngày sản xuất</th>
		<th>Tình trạng</th>
		<th></th>
	</tr>
	<c:forEach items="${foods}" var="f">
		<tr>
			<td>${f.foodId}</td>
			<td>${f.foodName}</td>
			<td><fmt:formatNumber value="${f.price}" type="number" /> đ</td>
			<td><img src="images/${f.picture}" width="100"/></td>
			<td><f:formatDate value="${f.createDate}" pattern="dd/MM/yyyy"/> </td>
			<td><c:choose>
				<c:when test="${f.active==true}">
					Còn hàng
				</c:when>
				<c:otherwise>
					Hết hàng
				</c:otherwise>
			</c:choose>
			</td>
			<th>
				<a href="FoodServlet?action=edit&foodid=${f.foodId}" class="btn btn-info">Sửa</a>
				<a href="FoodServlet?action=delete&foodid=${f.foodId}" onclick = "return confirm('Bạn có chắc chắn muốn xóa không?')" class="btn btn-danger">Xóa</a>
				<a href="FoodServlet?action=detail&foodid=${f.foodId}" class="btn btn-success">Chi tiết</a>
			</th>
		</tr>
	</c:forEach>
</table>