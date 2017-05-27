package component.custom.model;

import java.util.Date;

import component.common.model.BaseModel;

/** 订单 */
public class CustomOrder extends BaseModel {

	private int customId;// 客户id
	private String code;// 订单号
	private Date orderDate;// 订单日期
	private String remark;// 备注

	public int getCustomId() {
		return customId;
	}

	public void setCustomId(int customId) {
		this.customId = customId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
