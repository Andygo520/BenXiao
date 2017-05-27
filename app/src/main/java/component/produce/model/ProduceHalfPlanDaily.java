package component.produce.model;

import java.util.Date;

/** 半成品日计划 */
public class ProduceHalfPlanDaily {

	private int id;
	private int planId; // 半成品生产计划id
	private int processId;// 半成品工序id
	private Date produceDate;// 生产日期
	private int num;// 计划生产数量

	public ProduceHalfPlanDaily() {

	}

	public ProduceHalfPlanDaily(int planId, int processId, Date produceDate, int num) {
		this.planId = planId;
		this.processId = processId;
		this.produceDate = produceDate;
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

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
