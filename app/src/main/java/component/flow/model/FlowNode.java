package component.flow.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import component.system.model.SystemUser;

/** 节点 */
public class FlowNode {

	private int id;
	private int flowId; // 流程id
	private String name; // 名称
	private int seq;
	private Boolean isDel;

	@JSONField(serialize = false)
	private List<FlowField> fields;
	@JSONField(serialize = false)
	private List<SystemUser> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

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

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public List<FlowField> getFields() {
		return fields;
	}

	public void setFields(List<FlowField> fields) {
		this.fields = fields;
	}

	public List<SystemUser> getUsers() {
		return users;
	}

	public void setUsers(List<SystemUser> users) {
		this.users = users;
	}

}
