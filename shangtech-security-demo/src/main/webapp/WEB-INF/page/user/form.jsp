<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<form action="${ctx}/security/user/save" method="post" role="form">
<input type="hidden" name="id" value="${user.id}"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	<h4 class="modal-title" id="myModalLabel">用户编辑</h4>
</div>
<div class="modal-body">
	<div class="form-group">
		<label for="username">用户名</label>
		<input class="form-control" type="text" id="username" name="username" value="${user.username}"/>
	</div>
	<c:if test="${empty user.id}">
	<div class="form-group">
		<label for="username">密码</label>
		<input class="form-control" type="text" id="password" name="password"/>
	</div>
	</c:if>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-default btn-save">确定</button>
</div>
</form>