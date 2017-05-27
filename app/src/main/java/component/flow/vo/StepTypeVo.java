package component.flow.vo;

/** 审核步骤类型 */
public enum StepTypeVo {

	empty(null, ""),
	/** 保存 */
	save(1, "保存"),
	/** 提交 */
	commit(2, "提交"),
	/** 审核通过 */
	pass(3, "审核通过"),
	/** 审核未通过 */
	not_pass(4, "审核未通过"),
	/** 审核完成 */
	complete(5, "审核完成");

	private Integer key;
	private String value;

	private StepTypeVo(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static final StepTypeVo getInstance(Integer key) {
		for (StepTypeVo vo : values()) {
			if (key == null) {
				return empty;
			} else if (key == vo.getKey()) {
				return vo;
			}
		}
		throw new RuntimeException();
	}

}
