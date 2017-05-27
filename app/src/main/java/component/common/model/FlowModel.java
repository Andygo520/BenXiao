package component.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import component.flow.vo.AuditStatusVo;

public class FlowModel extends BaseModel {

	@JSONField(ordinal = 71)
	private int nodeId; // 流程节点id
	@JSONField(ordinal = 72)
	private int auditStatus; // 审核状态 - 1未提交2已提交3审核中4审核未通过5已审核

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}

	@JSONField(serialize = false)
	public AuditStatusVo getAuditStatusVo() {
		return AuditStatusVo.getInstance(auditStatus);
	}

}
