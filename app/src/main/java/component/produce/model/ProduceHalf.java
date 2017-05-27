package component.produce.model;

import component.common.model.BaseModel;

/** 半成品加工单 */
public class ProduceHalf extends BaseModel {

	private int planId;// 半成品生产计划id
	private int halfId;// 半成品id
	private String code;// 编号
	private String remark;// 备注
	private int status;// 状态 - 1未完工2已完工

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getHalfId() {
		return halfId;
	}

	public void setHalfId(int halfId) {
		this.halfId = halfId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
