package component.produce.model;

import java.util.Date;

import component.common.model.BaseModel;

/** 成品生产计划 */
public class ProduceProductPlan extends BaseModel {

	private int orderProductId;// 订单成品交付id
	private int productId;// 成品id
	private String code;// 编号
	private int num;// 计划生产数量
	private Date deliverDate;// 计划交付日期
	private Date startDate;// 生产开始日期
	private Date endDate;// 生产结束日期
	private String remark;// 备注

	public int getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
