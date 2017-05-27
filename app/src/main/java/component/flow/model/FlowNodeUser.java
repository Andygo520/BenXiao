package component.flow.model;

/** 节点用户设置 */
public class FlowNodeUser {

	private int id;
	private int nodeId; // 节点id
	private int userId; // 用户id

	public FlowNodeUser() {

	}

	public FlowNodeUser(int nodeId, int userId) {
		this.nodeId = nodeId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
