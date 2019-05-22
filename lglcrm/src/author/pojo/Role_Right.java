package author.pojo;

/**
 * @author lgl
 *角色和权限的多对多关系下的
 *连接表
 *角色权限表
 */
public class Role_Right {
	private int id;
	private int roleId;
	private int rightId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getRightId() {
		return rightId;
	}

	public void setRightId(int rightId) {
		this.rightId = rightId;
	}

}
