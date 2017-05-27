package component.flow.vo;

/** 审核状态 */
public enum AuditStatusVo {

	empty(null, ""),
	/** 未提交 */
	un_commit(1, "未提交"),
	/** 已提交 */
	committed(2, "已提交"),
	/** 审核中 */
	auditing(3, "审核中"),
	/** 审核未通过 */
	failure(4, "审核未通过"),
	/** 已审核 */
	audited(5, "已审核");

	private Integer key;
	private String value;

	private AuditStatusVo(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static final AuditStatusVo getInstance(Integer key) {
		for (AuditStatusVo vo : values()) {
			if (key == null) {
				return empty;
			} else if (key == vo.getKey()) {
				return vo;
			}
		}
		throw new RuntimeException();
	}

}
