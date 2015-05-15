<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" class="role-table" cellspacing="0" cellpadding="0">
	<tr>
		<td width="50%" valign="top">
			<ul class="list-group" id="roles">
				<c:forEach items="${roles}" var="role">
					<li class="list-group-item" data-id="${role.id}">${role.name}</li>
				</c:forEach>
			</ul>
		</td>
		<td width="70" valign="middle" align="center"
			style="border: none; padding: 0 15px;">
			<div class="btn-group-vertical">
				<button type="button" class="btn btn-default btn-add-role">添加</button>
				<button type="button" class="btn btn-default btn-remove-role">移除</button>
			</div>
		</td>
		<td width="50%" valign="top">
			<ul class="list-group" id="user-roles">
				<c:forEach items="${userRoles}" var="role">
					<li class="list-group-item" data-id="${role.id}">${role.name}</li>
				</c:forEach>
			</ul>
		</td>
	</tr>
</table>