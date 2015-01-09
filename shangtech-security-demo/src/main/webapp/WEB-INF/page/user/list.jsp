<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="../common.jsp"></jsp:include>
		<style type="text/css">
		.list-group-item{
			cursor:pointer;
		}
		.list-group-item:hover{
			background:#eee;
		}
		.list-group-item .btn-group{
			position:absolute;
			left:-107px;
			top:-1px;
			display:none;
			z-index:100;
		}
		.list-group-item:hover .btn-group{
			display:block;
		}
		.list-group-item .btn-group .btn{
			padding-top:10px;
			padding-bottom:10px;
		}
		.role-table td{
			border:1px solid #ddd;
			border-radius:4px;
			padding: 10px 0px;
		}
		.role-table td .list-group-item{
			border-left:0px;
			border-right:0px;
			border-radius:0px;
		}
		.role-table td .list-group-item:first{
			border-top:0px;
		}
		table{
			border-collapse: separate;
		}
		</style>
	</head>
	<body>
		<div class="container" style="margin-top:40px;">
			<div class="row">
				<div class="col-md-3">
					<ul class="list-group">
						<a class="list-group-item active" href="${ctx}/security/user/list">用户管理</a>
						<a class="list-group-item" href="${ctx}/security/resource/main">资源管理</a>
						<a class="list-group-item" href="${ctx}/security/role/list">角色管理</a>
						<a class="list-group-item" href="${ctx}/j_spring_security_logout">退出登录</a>
					</ul>
				</div>
				<div class="col-md-9">
					<div>
						<a href="javascript:;" class="btn btn-default add-user" data-url="${ctx}/security/user/form">添加用户</a>
					</div>
					<div class="row" style="margin-top:20px;">
						<div class="col-md-4">
							<ul class="list-group" id="user-list">
								<c:forEach items="${pagination.items}" var="item">
								<li class="list-group-item" data-id="${item.id}">
									${item.username}
									<div class="btn-group">
										<button class="btn btn-default btn-remove" type="button">删除</button>
										<button class="btn btn-default btn-edit" type="button">编辑</button>
									</div>
								</li>
								</c:forEach>
							</ul>
						</div>
						<div class="col-md-8" id="role-panel">
							
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal" id="user-modal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${ctx}/js/user.js"></script>
	</body>
</html>