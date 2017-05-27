package component.supply.model;

import component.common.model.FlowModel;

/** 采购计划 */
public class SupplyPurchasePlan extends FlowModel {

	private String code; // 编号
	private String name; // 名称
	private String remark;// 备注

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
