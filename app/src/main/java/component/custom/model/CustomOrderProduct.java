package component.custom.model;

import java.util.Date;

/** 订单成品交付 */
public class CustomOrderProduct {

	private int id;
	private int orderId;// 订单id
	private int productId;// 成品id
	private int num;// 交付数量
	private Date deliverDate;// 交付日期

	public CustomOrderProduct() {

	}

	public CustomOrderProduct(int orderId, int productId, int num, Date deliverDate) {
		this.orderId = orderId;
		this.productId = productId;
		this.num = num;
		this.deliverDate = deliverDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

}
