<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3 class="text-primary">Thêm mới món ăn</h3>
<form action="FoodServlet?action=add" method="post" class="mt-3">
    <div class="mb-3">
        <label for="foodid" class="form-label">Mã món ăn</label>
        <input type="text" class="form-control" id="foodid" name="foodid" placeholder="Nhập mã món ăn" required>
    </div>
    <div class="mb-3">
        <label for="foodname" class="form-label">Tên món ăn</label>
        <input type="text" class="form-control" id="foodname" name="foodname" placeholder="Nhập tên món ăn" required>
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Giá</label>
        <input type="number" class="form-control" id="price" name="price" placeholder="Nhập giá món ăn" min="0" value="0" required>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Mô tả</label>
        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Nhập mô tả món ăn"></textarea>
    </div>
    <div class="mb-3">
        <label for="picture" class="form-label">Ảnh</label>
        <input type="text" class="form-control" id="picture" name="picture" placeholder="Nhập đường dẫn ảnh">
    </div>
    <div class="form-check mb-3">
        <input type="checkbox" class="form-check-input" id="active" name="active" checked>
        <label class="form-check-label" for="active">Còn hàng</label>
    </div>
    <button type="submit" class="btn btn-primary">Lưu</button>
</form>
