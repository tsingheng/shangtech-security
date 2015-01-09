package net.shangtech.security.controller;

import java.util.ArrayList;
import java.util.List;

import net.shangtech.framework.controller.AjaxResponse;
import net.shangtech.framework.dao.support.Pagination;
import net.shangtech.security.entity.Role;
import net.shangtech.security.entity.User;
import net.shangtech.security.service.IRoleService;
import net.shangtech.security.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/security/user")
public class UserController {

	@Autowired IUserService userService;
	@Autowired IRoleService roleService;
	
	@RequestMapping({"/list"})
	public String list(Pagination<User> pagination, Model model){
		pagination = userService.findAllByPage(pagination);
		model.addAttribute("pagination", pagination);
		model.addAttribute("roles", roleService.findAll());
		return "user/list";
	}
	
	@RequestMapping("/form")
	public String form(Long id, Model model){
		User user = new User();
		if(id != null){
			user = userService.find(id);
		}
		model.addAttribute("user", user);
		return "user/form";
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResponse save(User user){
		if(user.getId() == null){
			userService.save(user);
		}
		else{
			userService.update(user);
		}
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@RequestMapping("/roles")
	public String roles(Long userId, Model model){
		List<Role> roles = roleService.findAll();
		List<Role> userRoles = roleService.findRolesByUserId(userId);
		if(!CollectionUtils.isEmpty(roles) && !CollectionUtils.isEmpty(userRoles)){
			userRoles.forEach(role -> {
				for(Role r : roles){
					if(r.getId().equals(role.getId())){
						roles.remove(r);
						break;
					}
				}
			});
		}
		model.addAttribute("roles", roles);
		model.addAttribute("userRoles", userRoles);
		return "user/roles";
	}
	
	@ResponseBody
	@RequestMapping("/add-role")
	public AjaxResponse addRoles(Long userId, @RequestParam(value = "roleIds[]", required = false) ArrayList<Long> roleIds){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		userService.addRoles(userId, roleIds);
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
	@ResponseBody
	@RequestMapping("/remove-role")
	public AjaxResponse removeRole(Long userId, @RequestParam(value = "roleIds[]", required = false) ArrayList<Long> roleIds){
		AjaxResponse ajaxResponse = AjaxResponse.instance();
		userService.removeRole(userId, roleIds);
		ajaxResponse.setSuccess(true);
		return ajaxResponse;
	}
	
}
