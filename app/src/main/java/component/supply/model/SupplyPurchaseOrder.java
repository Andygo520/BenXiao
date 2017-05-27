package component.supply.model;

import component.common.model.BaseModel;

/** 采购订单 */
public class SupplyPurchaseOrder extends BaseModel {

	private int planId; // 采购计划id
	private int supplyId; // 供应商id
	private String code; // 订单号
	private String remark; // 备注

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
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

}
