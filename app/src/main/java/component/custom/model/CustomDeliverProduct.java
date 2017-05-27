package component.custom.model;

import com.alibaba.fastjson.annotation.JSONField;

import component.product.model.Product;

/** 交付计划成品关系 */
public class CustomDeliverProduct {

	@JSONField(ordinal = 1)
	private int id;
	@JSONField(ordinal = 2)
	private int planId; // 交付计划id
	@JSONField(ordinal = 3)
	private int productId; // 成品id
	@JSONField(ordinal = 4)
	private int num; // 交付数量

	@JSONField(ordinal = 5)
	private Product product;

	public CustomDeliverProduct() {

	}

	public CustomDeliverProduct(int planId, int productId, int num) {
		this.planId = planId;
		this.productId = productId;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
