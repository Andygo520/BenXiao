package component.custom.vo;

import component.custom.vo.CustomTypeVo;

public enum CustomTypeVo {

	empty(null, ""),
	/** 普通 */
	general(1, "普通"),
	/** 优质 */
	quality(2, "优质");

	private Integer key;
	private String value;

	private CustomTypeVo(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static final CustomTypeVo getInstance(Integer key) {
		for (CustomTypeVo vo : values()) {
			if (key == null) {
				return empty;
			} else if (key == vo.getKey()) {
				return vo;
			}
		}
		throw new RuntimeException();
	}

}
