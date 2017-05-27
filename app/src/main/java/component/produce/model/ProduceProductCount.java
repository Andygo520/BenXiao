package component.produce.model;

/** 成品工序计件 */
public class ProduceProductCount {

	private int id;
	private int produceDailyId;// 成品子加工单id
	private int stationId; // 机台工位id
	private int userId;// 操作工id
	private int produceNum;// 生产数量
	private int successNum;// 合格品数量

	public ProduceProductCount() {

	}

	public ProduceProductCount(int produceDailyId, int stationId, int userId, int produceNum, int successNum) {
		this.produceDailyId = produceDailyId;
		this.stationId = stationId;
		this.userId = userId;
		this.produceNum = produceNum;
		this.successNum = successNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduceDailyId() {
		return produceDailyId;
	}

	public void setProduceDailyId(int produceDailyId) {
		this.produceDailyId = produceDailyId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(int produceNum) {
		this.produceNum = produceNum;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}

}
