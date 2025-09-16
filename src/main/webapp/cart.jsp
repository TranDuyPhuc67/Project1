<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .banner {
   		 text-align: center;
		}
        .banner img {
            width: 70%;
            height: auto;
        }
        .cart-table th, .cart-table td {
            vertical-align: middle;
        }
        .cart-item img {
            width: 100px;
            height: auto;
        }
        .cart-item {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #fff;
        }
        .cart-total {
            font-size: 20px;
            font-weight: bold;
            margin-top: 20px;
        }
        .btn-action {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="banner">
    <img src="images/banner.jpg" alt="Giỏ hàng" class="img-fluid" />
</div>

<div class="container mt-4">
    <h4>Giỏ hàng của bạn</h4>

    <c:if test="${not empty cartItems}">
        <div class="row">
            <div class="col-12">
                <table class="table table-bordered cart-table">
                    <thead>
                        <tr>
                            <th>Mã số</th>
                            <th>Tên món ăn</th>
                            <th>Giá</th>
                            <th>Ảnh</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cartItems}" var="item">
                            <tr class="cart-item">
                                <td>${item.foodId}</td>
                                <td>${item.foodName}</td>
                                <td><fmt:formatNumber value="${item.price}" type="number" /> đ</td>
                                <td><img src="images/${item.picture}" alt="${item.foodName}"/></td>
                                <td>
                                    <input type="number" value="${item.quantity}" min="1" class="form-control" />
                                </td>
                                <td><fmt:formatNumber value="${item.getTotalPrice()}" type="number" /> đ</td>
                                <td>
                                    <button class="btn btn-primary btn-action">Cập nhật</button>
                                    <button class="btn btn-danger btn-action">Xóa</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="cart-total">
                    Tổng cộng: <fmt:formatNumber value="${totalAmount}" type="number" /> đ
                </div>
                
                <div class="text-right mt-4">
                    <button class="btn btn-success">Tiến hành thanh toán</button>
                </div>
            </div>
        </div>
    </c:if>
    
    <c:if test="${empty cartItems}">
        <div class="alert alert-warning">
            Giỏ hàng của bạn hiện tại chưa có sản phẩm nào.
        </div>
    </c:if>
</div>

<!-- Bootstrap JS & jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
