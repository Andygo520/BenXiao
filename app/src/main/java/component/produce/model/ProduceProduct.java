package component.produce.model;

import component.common.model.BaseModel;

/** 成品加工单 */
public class ProduceProduct extends BaseModel {

	private int planId;// 成品生产计划id
	private int productId;// 成品id
	private String code;// 编号
	private String remark;// 备注
	private int status;// 状态 - 1未完工2已完工

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
