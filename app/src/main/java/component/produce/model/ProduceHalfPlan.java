package component.produce.model;

import java.util.Date;

import component.common.model.BaseModel;

/** 半成品生产计划 */
public class ProduceHalfPlan extends BaseModel {

	private int halfId;// 半成品id
	private String code;// 编号
	private int num;// 计划生产数量
	private Date startDate;// 生产开始日期
	private Date endDate;// 生产结束日期
	private String remark;// 备注

	public int getHalfId() {
		return halfId;
	}

	public void setHalfId(int halfId) {
		this.halfId = halfId;
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
