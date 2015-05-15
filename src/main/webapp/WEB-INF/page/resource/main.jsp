<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common.jsp"></jsp:include>
		<link rel="stylesheet" href="${ctx}/js/zTree_v3/css/zTreeStyle/zTreeStyle.css">
		<script type="text/javascript" src="${ctx}/js/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
	</head>
	<body>
		<div class="container" style="margin-top:40px;">
			<div class="row">
				<div class="col-md-3">
					<ul class="list-group">
						<a class="list-group-item" href="${ctx}/security/user/list">用户管理</a>
						<a class="list-group-item active" href="${ctx}/security/resource/main">资源管理</a>
						<a class="list-group-item" href="${ctx}/security/role/list">角色管理</a>
						<a class="list-group-item" href="${ctx}/j_spring_security_logout">退出登录</a>
					</ul>
				</div>
				<div class="col-md-9">
					<ul id="resource-tree" class="ztree"></ul>
				</div>
			</div>
		</div>
		
		<div class="modal" id="resource-modal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					
				</div>
			</div>
		</div>
		
		<ul class="dropdown-menu context-menu" id="resource-tree-menu" style="display:none;">
			<li><a href="javascript:;" class="edit">编辑</a></li>
			<li><a href="javascript:;" class="add-child">添加资源</a></li>
			<li class="divider"></li>
			<li><a href="javascript:;" class="remove">删除资源</a></li>
		</ul>
		<script type="text/javascript" src="${ctx}/js/resource.js"></script>
	</body>
</html>