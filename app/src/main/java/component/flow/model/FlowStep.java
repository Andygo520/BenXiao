package component.flow.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import component.flow.vo.StepTypeVo;

/** 步骤 */
public class FlowStep {

	private int id;
	private int dataId;// 业务数据id
	private int flowId;// 流程id
	private int nodeId;// 节点id
	private int userId;// 用户id
	private int type; // 类型 - 1保存2提交3审核通过4审核未通过5审核完成
	private Date ctime;
	private Boolean isDel;

	public FlowStep() {

	}

	public FlowStep(int dataId, int flowId, int nodeId, int userId, int type, Date ctime) {
		this.dataId = dataId;
		this.flowId = flowId;
		this.nodeId = nodeId;
		this.userId = userId;
		this.type = type;
		this.ctime = ctime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	@JSONField(serialize = false)
	public StepTypeVo getStepTypeVo() {
		return StepTypeVo.getInstance(type);
	}

}
