package component.custom.model;

import com.alibaba.fastjson.annotation.JSONField;

import component.common.model.BaseModel;
import component.custom.vo.CustomTypeVo;

/** 供应商 */
public class Custom extends BaseModel {

	@JSONField(ordinal = 1)
	private String code; // 编码
	@JSONField(ordinal = 2)
	private String name; // 名称
	@JSONField(ordinal = 3)
	private Integer type; // 类型 - 1普通2优质
	@JSONField(ordinal = 4)
	private String tel; // 电话
	@JSONField(ordinal = 5)
	private String fax; // 传真
	@JSONField(ordinal = 6)
	private String address; // 地址
	@JSONField(ordinal = 7)
	private String contact; // 联系人
	@JSONField(ordinal = 8)
	private String job; // 职位
	@JSONField(ordinal = 9)
	private String phone; // 手机号
	@JSONField(ordinal = 10)
	private String email; // 邮箱

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JSONField(serialize = false)
	public CustomTypeVo getCustomTypeVo() {
		return CustomTypeVo.getInstance(type);
	}

}
