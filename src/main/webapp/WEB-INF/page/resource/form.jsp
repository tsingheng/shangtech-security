<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<form class="form-horizontal" action="${ctx}/security/resource/save" method="post" role="form">
<input type="hidden" name="id" value="${resource.id}"/>
<input type="hidden" name="parentId" value="${resource.parentId}"/>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	<h4 class="modal-title" id="myModalLabel">资源编辑</h4>
</div>
<div class="modal-body">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">资源名称</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" id="name" name="name" value="${resource.name}"/>
		</div>
	</div>
	<div class="form-group">
		<label for="code" class="col-sm-2 control-label">资源编码</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" id="code" name="code" value="${resource.code}"/>
		</div>
	</div>
	<div class="form-group">
		<label for="code" class="col-sm-2 control-label">资源路径</label>
		<div class="col-sm-10">
			<input class="form-control" type="text" id="path" name="path" value="${resource.path}"/>
		</div>
	</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-default btn-save">确定</button>
</div>
</form>