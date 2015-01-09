package net.shangtech.security.controller.vo;

import java.io.Serializable;

import net.shangtech.security.entity.Resource;

public class AuthTreeNode implements Serializable {

    private static final long serialVersionUID = -8038254735948624423L;

	private Long id;
	
	private String name;
	
	private Long pId;
	
	private Boolean open;
	
	private Boolean checked;
	
	public static final AuthTreeNode ROOT = new AuthTreeNode();
	
	static{
		ROOT.setId(Resource.DEFAULT_ROOT_ID);
		ROOT.setName("ROOT");
		ROOT.setOpen(true);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
