<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
    .banner {
        text-align: center;
    }

    .banner img {
        width: 100%; /* Hoặc kích thước bạn muốn */
        height: auto; /* Chiều cao tự động */
    }
</style>
<div class="banner">
    <img src="images/banner.jpg" alt="Banner Trang Chủ" class="img-fluid" />
</div>

<br>

<h4>Danh mục sản phẩm</h4>

<table class="table table-bordered">
	<tr>
		<th>Mã số</th>
		<th>Tên món ăn</th>
		<th>Giá</th>
		<th>Ảnh</th>
		<th>Ngày sản xuất</th>
		<th>Tình trạng</th>
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
		</tr>
	</c:forEach>
</table>