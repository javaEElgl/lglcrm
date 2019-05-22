package author.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lgl
 *权限类
 *描述权限
 *权限类和角色类是多对多关系
 */
public class Right {
	private int rightId;
	private String rightName;
	private Set<Role> roles=new HashSet<Role>();
	public int getRightId() {
		return rightId;
	}
	public void setRightId(int rightId) {
		this.rightId = rightId;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
