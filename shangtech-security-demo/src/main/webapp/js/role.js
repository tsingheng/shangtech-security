$(document).ready(function(){
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
	};
	authTree = null;
	$('.add-role').click(function(){
		$('#role-modal .modal-content').load($(this).data('url'));
		$('#role-modal').modal('show');
	});
	$('body').on('click', '.btn-edit', function(){
		var id = $(this).closest('.list-group-item').data('id');
		$('#role-modal .modal-content').load(ctx + '/security/role/form?id=' + id);
		$('#role-modal').modal('show');
	});
	$('body').on('click', '.btn-save', function(){
		$(this).closest('form').ajaxSubmit({
			success: function(data){
				document.location.reload();
			}
		});
	});
	$('body').on('click', '.role-list .list-group-item', function(){
		if($(this).hasClass('active')){
			return;
		}
		$('.role-list .active').removeClass('active');
		$(this).addClass('active');
		$('.auth-panel .panel-heading').html($(this).text());
		$('.auth-panel .panel-footer').show();
		var roleId = $(this).data('id');
		$.ajax({
			url: ctx + '/security/role/auth-tree?roleId=' + roleId,
			success: function(data){
				authTree = $.fn.zTree.init($("#auth-tree"), setting, data);
			}
		});
	});
	$('#save-auth').click(function(){
		saveAuthEvent();
	});
	function saveAuthEvent(){
		$('#save-auth').unbind().html('正在保存').attr('disabled', 'disabled');
		var checkedNodes = authTree.getCheckedNodes(true);
		var resourceIds = [];
		for(var i in checkedNodes){
			resourceIds.push(checkedNodes[i].id);
		}
		$.ajax({
			url: ctx + '/security/role/auth',
			type: 'POST',
			data: {
				resourceIds: resourceIds,
				roleId: $('.role-list .active').data('id')
			},
			success: function(data){
				$('#save-auth').html('保存').removeAttr('disabled');
				$('#save-auth').click(function(){
					saveAuthEvent();
				});
				if(data.success){
					alert('保存成功');
				}
				else{
					alert('保存失败');
				}
			}
		});
	}
});