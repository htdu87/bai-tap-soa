<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-8">
        <div class="box box-solid box-primary" id="box-lst">
            <div class="box-header">
                <h3 class="box-title">Danh sách khách hàng</h3>
            </div>
            <div class="box-body">
                <form name="frmSearch" id="frmSearch" method="post">
                    <div class="row">
                        <div class="col-md-offset-6 col-md-6 form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="MKH, Tên khách hàng, SĐT" name="keyword" id="frmSearch-keyword">
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-primary btn-block btn-flat" title="Tìm kiếm" id="frmSearch-search"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="box-footer table-responsive">
                <h5 class="text-center"><b>${DEL_MSG }</b></h5>
                <table class="table table-striped table-hover">
                	<thead>
                		<tr>
                			<th>Mã KH</th>
                			<th>Tên khách hàng</th>
                			<th>Địa chỉ</th>
                			<th>Số ĐT</th>
                			<th>Thao tác</th>
                		</tr>
                	</thead>
                	<tbody>
                		<c:forEach items="${DS_KH}" var="kh">
                			<tr>
	                			<td>${kh.getMaKhachHang() }</td>
	                			<td>${kh.getTenKhachHang() }</td>
	                			<td>${kh.getDiaChi() }</td>
	                			<td>${kh.getSoDienThoai() }</td>
	                			<td>
	                				<a href="?act=edit&id=${kh.getIdKhachHang() }" title="Cập nhật">
	                					<i class="fa fa-edit"></i>
	                				</a>&nbsp;
	                				<a href="?act=del&id=${kh.getIdKhachHang() }" title="Xóa" class="act-del" onclick="return confirm('Bạn chắc muốn xóa khách hàng này?')">
	                					<i class="text-danger fa fa-trash"></i>
	                				</a>
                				</td>
	                		</tr>
                		</c:forEach>
                	</tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-4">
    	<form action="HomeController" method="post">
	    	<div class="box box-solid box-success" id="box-form">
	    		<div class="box-header">
	                <h3 class="box-title">Thông tin khách hàng</h3>
	            </div>
	            <div class="box-body">
            		<h5 class="text-center"><b>${SAVE_MSG }</b></h5>
            		<div class="form-group">
                        <label>Mã khách hàng <span class="text-danger" title="Không được rỗng">*</span></label>
                        <input type="hidden" name="idKhachHang" value="${KH_IDKH}">
                        <input type="text" maxlength="50" class="form-control" name="maKhachHang" required value="${KH_MAKH }">
                    </div>
                    <div class="form-group">
                        <label>Tên khách hàng <span class="text-danger" title="Không được rỗng">*</span></label>
                        <input type="text" maxlength="250" class="form-control" name="tenKhachHang" required value="${KH_TKH }">
                    </div>
                    <div class="form-group">
                        <label>Địa chỉ</label>
                        <input type="text" maxlength="250" class="form-control" name="diaChi" value="${KH_DC }">
                    </div>
                    <div class="form-group">
                        <label>Số điện thoại</label>
                        <input type="text" maxlength="50" class="form-control" name="soDienThoai" value="${KH_SDT }">
                    </div>
	            </div>
	            <div class="box-footer text-center">
	            	<button class="btn btn-success btn-flat" type="submit"><i class="fa fa-check-circle"></i> Lưu</button>
	                <a href="HomeController" class="btn btn-default btn-flat" role="button"><i class="fa fa-times-circle"></i> Hủy</a>
	            </div>
	    	</div>
    	</form>
    </div>
</div>