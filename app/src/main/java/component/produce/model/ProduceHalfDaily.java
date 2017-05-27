package component.produce.model;

import java.util.Date;

/** 半成品子加工单 */
public class ProduceHalfDaily {

	private int id;
	private int produceId;// 半成品加工单id
	private int processId;// 半成品工序id
	private String code;// 编号
	private Date produceDate;// 生产日期
	private int num;// 计划生产数量

	public ProduceHalfDaily() {

	}

	public ProduceHalfDaily(int produceId, int processId, String code, Date produceDate, int num) {
		this.produceId = produceId;
		this.processId = processId;
		this.code = code;
		this.produceDate = produceDate;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduceId() {
		return produceId;
	}

	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
