package author.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lgl
 *��ɫ��
 *����������ɫ����Ϣ
 *�����ɫ���е�Ȩ���б�
 */
public class Role {
		private  int roleId;
		private String roleName;
		private Set<User> users=new HashSet<User>();
		private Set<Right> rights=new HashSet<Right>();
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public Set<User> getUsers() {
			return users;
		}
		public void setUsers(Set<User> users) {
			this.users = users;
		}
		public Set<Right> getRights() {
			return rights;
		}
		public void setRights(Set<Right> rights) {
			this.rights = rights;
		}
		
}
