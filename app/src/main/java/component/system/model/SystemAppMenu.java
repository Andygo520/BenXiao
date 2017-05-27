package component.system.model;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import component.common.model.TreeModel;

/** 菜单 */
public class SystemAppMenu extends TreeModel {

	@JSONField(ordinal = 1)
	private String name; // 菜单名称

	@JSONField(ordinal = 2)
	private List<SystemAppMenu> subList;
	@JSONField(ordinal = 3)
	private List<SystemRole> roles;

	public SystemAppMenu() {

	}

	public SystemAppMenu(String name, int pid, int seq, Boolean isLeaf, int cid, Date ctime) {
		this.name = name;
		this.pid = pid;
		this.seq = seq;
		this.isLeaf = isLeaf;
		this.cid = cid;
		this.ctime = ctime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SystemAppMenu> getSubList() {
		return subList;
	}

	public void setSubList(List<SystemAppMenu> subList) {
		this.subList = subList;
	}

	public List<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}

}
