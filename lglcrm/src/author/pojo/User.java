package author.pojo;

/**
 * @author lgl
 *用户类
 *用来登录，以及获取用户权限根据权限生成不同的菜单页面
 *一个用户只有一个角色，一个角色有多个用户，这是一对多关系
 */
public class User {
		private int userId;
		private String username;
		private String password;
		private Role role;
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		
}
