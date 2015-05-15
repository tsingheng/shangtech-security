<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/WEB-INF/page/common.jsp"></jsp:include>
	</head>
	<body>
		<div class="login-wrapper" style="margin-top:50px;">
			<div class="modal" style="display:block;position:relative;">
		  		<div class="modal-dialog" style="width:450px;">
		  		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<div class="alert alert-warning">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
				</c:if>
		  		<form class="form" method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
		    		<div class="modal-content">
		      			<div class="modal-body">
		        			<div class="form-group">
		        				<div class="input-group">
		        					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		        					<input type="text" name="j_username" value="guest" class="form-control" placeholder="请输入系统账号">
		        				</div>
		        			</div>
		        			<div class="form-group">
		        				<div class="input-group">
		        					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        					<input type="password" name="j_password" value="123456" class="form-control" placeholder="请输入账号密码">
		        				</div>
		        			</div>
		      			</div>
		      			<div class="modal-footer">
		        			<input type="submit" class="btn btn-primary" value="登  录">
		      			</div>
		    		</div>
		    	</form>
		 		</div>
			</div>
		</div>
	</body>
</html>