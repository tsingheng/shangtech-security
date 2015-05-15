package net.shangtech.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shangtech.framework.web.controller.AjaxResponse;
import net.shangtech.security.entity.Resource;
import net.shangtech.security.service.IResourceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/security/resource")
public class ResourceController {
	
	@Autowired private IResourceService resourceService;
	
	@RequestMapping("/main")
	public String main(){
		return "resource/main";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<Map<String, Object>> list(Long parentId){
		List<Map<String, Object>> array = new ArrayList<>();
		List<Resource> list = resourceService.findByParentId(parentId);
		if(!CollectionUtils.isEmpty(list)){
			for(Resource resource : list){
				Map<String, Object> object = new HashMap<>();
				object.put("id", resource.getId());
				object.put("name", resource.getName());
				if(StringUtils.isBlank(resource.getPath())){
					object.put("isParent", true);
				}
				else{
					object.put("isParent", false);
				}
				array.add(object);
			}
		}
		return array;
	}
	
	@RequestMapping("/form")
	public String form(Long id, Long parentId, Model model){
		Resource resource = new Resource();
		if(id == null){
			resource.setParentId(parentId);
		}
		else{
			resource = resourceService.find(id);
		}
		model.addAttribute("resource", resource);
		return "resource/form";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResponse save(Resource resource){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		if(resource.getId() == null){
			resourceService.save(resource);
		}
		else{
			resourceService.update(resource);
		}
		ajaxResponse.setSuccess(true);
		ajaxResponse.setData(resource);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/remove")
	public AjaxResponse remove(Long id){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		resourceService.delete(id);
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
}
