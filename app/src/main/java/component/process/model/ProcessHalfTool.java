package component.process.model;

import java.io.Serializable;

/** 半成品工序-工装 */
@SuppressWarnings("serial")
public class ProcessHalfTool implements Serializable {

	private int id;
	private int processId;
	private int toolId;

	public ProcessHalfTool() {

	}

	public ProcessHalfTool(int processId, int toolId) {
		this.processId = processId;
		this.toolId = toolId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getToolId() {
		return toolId;
	}

	public void setToolId(int toolId) {
		this.toolId = toolId;
	}

}
