package component.custom.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import component.common.model.BaseModel;

/** 交付计划 */
public class CustomDeliverPlan extends BaseModel {

	@JSONField(ordinal = 2)
	private int orderId; // 订单id
	@JSONField(ordinal = 3)
	private String code; // 编号
	@JSONField(ordinal = 4)
	private String remark; // 备注

	@JSONField(ordinal = 5)
	private List<CustomDeliverProduct> deliverProducts;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public List<CustomDeliverProduct> getDeliverProducts() {
		return deliverProducts;
	}

	public void setDeliverProducts(List<CustomDeliverProduct> deliverProducts) {
		this.deliverProducts = deliverProducts;
	}

}
