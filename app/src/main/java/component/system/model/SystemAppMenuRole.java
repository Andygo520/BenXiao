package component.system.model;

/** 用户角色关系 */
public class SystemAppMenuRole {

	private int id;
	private int appMenuId; // app菜单id
	private int roleId; // 角色id

	public SystemAppMenuRole() {

	}

	public SystemAppMenuRole(int appMenuId, int roleId) {
		this.appMenuId = appMenuId;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppMenuId() {
		return appMenuId;
	}

	public void setAppMenuId(int appMenuId) {
		this.appMenuId = appMenuId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
