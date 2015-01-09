$(document).ready(function(){
	var resourceTree = $.fn.zTree.init($('#resource-tree'), {
		async: {
			enable: true,
			url: ctx + '/security/resource/list',
			autoParam: ['id=parentId']
		},
		callback: {
			onRightClick: function(e, treeId, treeNode){
				if(treeNode && !treeNode.noR){
					resourceTree.selectNode(treeNode);
					$('#resource-tree-menu').css({
						left: e.clientX + 'px',
						top: e.clientY + 'px',
						display: 'block'
					});
				}
			}
		}
	}, {
		id: 0,
		name: 'ROOT',
		isParent: true
	});
	
	$(document).bind('mousedown', function(e){
		if(e.target.id == 'resource-tree-menu' || $(e.target).closest('#resource-tree-menu').length > 0)
			return;
		$('#resource-tree-menu').hide();
	});
	
	$('body').on('click', '#resource-tree-menu .add-child', function(){
		var selected = resourceTree.getSelectedNodes();
		if(!selected) return;
		var parent = selected[0];
		$('#resource-modal .modal-content').load(ctx + '/security/resource/form?parentId=' + parent.id);
		$('#resource-modal').modal('show');
		$('#resource-tree-menu').hide();
	});
	
	$('body').on('click', '#resource-tree-menu .edit', function(){
		var selected = resourceTree.getSelectedNodes();
		if(!selected) return;
		var resource = selected[0];
		$('#resource-modal .modal-content').load(ctx + '/security/resource/form?id=' + resource.id);
		$('#resource-modal').modal('show');
		$('#resource-tree-menu').hide();
	});
	
	$('body').on('click', '.btn-save', function(){
		$(this).closest('form').ajaxSubmit({
			success: function(data){
				var resource = resourceTree.getNodeByParam('id', data.data.id);
				if(resource){
					//for edit
					data.data.isParent = resource.isParent;
					$.extend(resource, data.data);
					resourceTree.updateNode(resource);
				}
				else {
					//for add
					var parent = resourceTree.getNodeByParam('id', data.data.parentId);
					if(!parent.isParent){
						parent.isParent = true;
					}
					resourceTree.reAsyncChildNodes(parent, 'refresh');
				}
				$('#resource-modal').modal('hide');
			}
		});
	});
});