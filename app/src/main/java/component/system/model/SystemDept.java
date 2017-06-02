package component.system.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import component.common.model.BaseModel;

/** 部门 */
public class SystemDept extends BaseModel {

	@JSONField(ordinal = 1)
	private String name;
	@JSONField(ordinal = 2)
	private int seq;

	@JSONField(serialize = false)
	private List<SystemUser> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public List<SystemUser> getUsers() {
		return users;
	}

	public void setUsers(List<SystemUser> users) {
		this.users = users;
	}

}
