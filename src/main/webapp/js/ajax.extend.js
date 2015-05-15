(function($){
	var _ajax = $.ajax;
	$.extend($, {
		ajax: function(opts){
			var success = opts.success;
			var error = opts.error;
			$.extend(opts, {
				success: function(data, textStatus){
					if(success){
						success(data, textStatus);
					}
				},
				error: function(xhr, textStatus, e){
					if(error){
						error(xhr, textStatus, e);
						return;
					}
					if(xhr.status == '403'){
						alert('没有权限');
					}
				}
			});
			return _ajax(opts);
		}
	});
})(jQuery);