package net.shangtech.security.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.shangtech.framework.controller.AjaxResponse;
import net.shangtech.framework.dao.support.Pagination;
import net.shangtech.security.controller.vo.AuthTreeNode;
import net.shangtech.security.entity.Resource;
import net.shangtech.security.entity.Role;
import net.shangtech.security.service.IResourceService;
import net.shangtech.security.service.IRoleService;
import net.shangtech.security.support.SecurityMetadataSource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security/role")
public class RoleController {

	@Autowired private IRoleService roleService;
	@Autowired private IResourceService resourceService;
	@Autowired private SecurityMetadataSource securityMetadataSource;
	
	@RequestMapping("/list")
	public String list(Pagination<Role> pagination, Model model){
		pagination = roleService.findAllByPage(pagination);
		model.addAttribute("pagination", pagination);
		return "role/list";
	}
	
	@RequestMapping("/form")
	public String form(Long id, Model model){
		Role role = new Role();
		if(id != null){
			role = roleService.find(id);
		}
		model.addAttribute("role", role);
		return "role/form";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResponse save(Role role){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		if(role.getId() == null){
			roleService.save(role);
		}
		else{
			roleService.update(role);
		}
		securityMetadataSource.clear();
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/auth")
	public AjaxResponse auth(Long roleId, @RequestParam(value = "resourceIds[]", required = false) ArrayList<Long> resourceIds){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		roleService.auth(roleId, resourceIds);
		securityMetadataSource.clear();
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/add-auth")
	public AjaxResponse addAuth(Long roleId, Long resourceId){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		roleService.auth(roleId, resourceId);
		securityMetadataSource.clear();
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/remove-auth")
	public AjaxResponse removeAuth(Long roleId, Long resourceId){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		roleService.removeAuth(roleId, resourceId);
		securityMetadataSource.clear();
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/auth-tree")
	public List<AuthTreeNode> authTree(Long roleId){
		AuthTreeNode root = new AuthTreeNode();
		root.setId(Resource.DEFAULT_ROOT_ID);
		root.setName("ROOT");
		root.setOpen(true);
		List<AuthTreeNode> list = new LinkedList<>();
		list.add(root);
		List<Resource> resources = resourceService.findAll();
		List<Long> authedResources = roleId != null ? resourceService.findIdsByRoleId(roleId) : null;
		if(!CollectionUtils.isEmpty(resources)){
			resources.forEach(resource -> {
				AuthTreeNode node = new AuthTreeNode();
				if(CollectionUtils.containsInstance(authedResources, resource.getId())){
					node.setChecked(true);
				}
				node.setId(resource.getId());
				node.setName(resource.getName());
				if(StringUtils.isBlank(resource.getPath())){
					node.setOpen(true);
				}
				node.setOpen(true);
				node.setpId(resource.getParentId());
				list.add(node);
			});
			for(AuthTreeNode node : list){
				if(BooleanUtils.isTrue(node.getChecked())){
					root.setChecked(true);
					break;
				}
			}
		}
		return list;
	}
}
