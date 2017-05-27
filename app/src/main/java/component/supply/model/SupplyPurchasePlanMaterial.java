package component.supply.model;

/** 计划原料采购 */
public class SupplyPurchasePlanMaterial {

	private int id;
	private int planId; // 采购计划id
	private int materialId; // 原料id
	private int num; // 采购数量

	public SupplyPurchasePlanMaterial() {

	}

	public SupplyPurchasePlanMaterial(int planId, int materialId, int num) {
		this.planId = planId;
		this.materialId = materialId;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
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

}
