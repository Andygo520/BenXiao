package component.supply.model;

import java.util.Date;

/** 订单原料采购 */
public class SupplyPurchaseOrderMaterial {

	private int id;
	private int orderId;// 采购订单id
	private int materialId;// 原料id
	private int num;// 采购数量
	private Date arrivalDate;// 到货日期

	public SupplyPurchaseOrderMaterial() {

	}

	public SupplyPurchaseOrderMaterial(int orderId, int materialId, int num, Date arrivalDate) {
		this.orderId = orderId;
		this.materialId = materialId;
		this.num = num;
		this.arrivalDate = arrivalDate;
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

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

}
