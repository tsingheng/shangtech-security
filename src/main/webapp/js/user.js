$(document).ready(function(){
	$('.add-user').click(function(){
		$('#user-modal .modal-content').load($(this).data('url'));
		$('#user-modal').modal('show');
	});
	$('body').on('click', '.btn-save', function(){
		$(this).closest('form').ajaxSubmit({
			success: function(data){
				document.location.reload();
			}
		});
	});
	$('body').on('click', '.btn-edit', function(){
		var id = $(this).closest('.list-group-item').data('id');
		$('#user-modal .modal-content').load(ctx + '/security/user/form?id=' + id);
		$('#user-modal').modal('show');
	});
	$('body').on('click', '#user-list .list-group-item', function(){
		if($(this).hasClass('active')){
			return;
		}
		$('#user-list .list-group-item.active').removeClass('active');
		$(this).addClass('active');
		$('#role-panel').load(ctx + '/security/user/roles?userId=' + $(this).data('id'));
	});
	$('body').on('click', '#role-panel .list-group-item', function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
			return;
		}
		$(this).addClass('active');
	});
	$('body').on('click', '#role-panel .btn-add-role', function(){
		var roleIds = [];
		$('#roles .list-group-item.active').each(function(){
			roleIds.push($(this).data('id'));
		});
		$.ajax({
			url: ctx + '/security/user/add-role',
			data: {
				userId: $('#user-list .active').data('id'),
				roleIds: roleIds
			},
			type: 'POST',
			success: function(){
				$('#user-roles').append($('#roles .list-group-item.active').removeClass('active'));
			}
		});
	});
	$('body').on('click', '#role-panel .btn-remove-role', function(){
		var roleIds = [];
		$('#user-roles .list-group-item.active').each(function(){
			roleIds.push($(this).data('id'));
		});
		$.ajax({
			url: ctx + '/security/user/remove-role',
			data: {
				userId: $('#user-list .active').data('id'),
				roleIds: roleIds
			},
			type: 'POST',
			success: function(){
				$('#roles').append($('#user-roles .list-group-item.active').removeClass('active'));
			}
		});
	});
});