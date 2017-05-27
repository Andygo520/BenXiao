package component.flow.model;

import java.util.Date;

/** 意见 */
public class FlowOpinion {

	private int id;
	private int dataId;// 业务数据id
	private int flowId;// 流程id
	private int nodeId;// 节点id
	private int userId;// 用户id
	private String code;// 编码
	private String content;// 内容
	private Date ctime;
	private Boolean isDel;

	public FlowOpinion() {

	}

	public FlowOpinion(int dataId, int flowId, int nodeId, int userId, String code, String content, Date ctime) {
		this.dataId = dataId;
		this.flowId = flowId;
		this.nodeId = nodeId;
		this.userId = userId;
		this.code = code;
		this.content = content;
		this.ctime = ctime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

}
