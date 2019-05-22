package author.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import utils.page.Page;
import author.pojo.Right;
import author.pojo.Role;
import author.pojo.Role_Right;
import author.pojo.User;
import author.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.mail.iap.Response;


/**
 * @author lgl
 *UserAction
 *这是用来操作UserAction的相关信息
 */
public class UserAction extends ActionSupport {
	private User u;
	private Page pager;   //用于获取分页查询显示
	private UserService userService;
	
	
	public Page getPager() {
		return pager;
	}

	public void setPager(Page pager) {
		this.pager = pager;
	}
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	//登录功能
	public String login() {
		User user = userService.login(u.getUsername(), u.getPassword());
		// 登陆成功
		if (user != null) {
			//取出角色id，更具角色id找到角色并赋值给用户
			int roleId=user.getRole().getRoleId();
			Role role=userService.find_RoleByID(roleId);
			user.setRole(role);
			//获取当前登录用户具有的权限，把权限和登录用户保存到session中
			List<Right> ri=userService.find_Right(roleId);
			Map<String, Object> session = ActionContext.getContext().getSession();
			//在session中保存user对象
			session.put("user", user);
			//在session中保存权限
			session.put("rights", ri);
			return "login_success";
		} else {
			ActionContext.getContext().put("loginError", "用户名密码错误");
			return "error";
		}
	}
	
	//退出功能
	public String exit(){
		ServletActionContext.getRequest().getSession().invalidate();  //清空session
		return "exit";
	}
	
	
	public String find(){
		//扫描一下最新的角色表
		List<Role> rlist = userService.find_Role();
		Map<String, Object> session = ActionContext.getContext().getSession();
		//把所有角色保存在session域中
		session.put("rolelist",rlist);
		//查询出用户分页显示
		pager = userService.splitPage(pager.getPageIndex(),pager.getPageSize());
		List<User> list = pager.getList();
		session.put("userlist",list);
		return "alluser";
	}
	
	//根据用户名进行查询
	public String findName(){
		if("".equals(u.getUsername())){
			return this.find();
		}
		List<User> list = userService.find(u);
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("userlist",list);
		return "findName";
	}
	
	//实现中转效果
	public String toadd(){
		return "toadd";
	}
	
	//添加用户
	public String add(){
		boolean flag = userService.add(u);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(flag == false){
			request.setAttribute("error_message", "新增用户失败！");
			return "error";
		}
		return this.find();
	}
	
	//ajax校验用户名是否存在
	public String nameCheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = request.getParameter("name");
		User user=new User();
		user.setUsername(name);
		List<User> list = userService.find(user);
		if(list.size()>0){
			try {
				response.getWriter().print("no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().print("yes");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	//删除用户
	public String del(){
		boolean flag = userService.del(u);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(flag == false){
			request.setAttribute("error_message", "删除用户失败！");
			return "error";
		}
		return find();
	}
	
	//编辑用户先获取要编辑用户的所有信息
	public String edit(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<User> list = (List<User>) session.get("userlist");
		//循环找到我们编辑的user对象绑定到request中
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUserId() == u.getUserId()){
//				System.out.println(list.get(i).getUsername());
//				System.out.println(list.get(i).getPassword());
				request.setAttribute("edituser", list.get(i));
				return "edit";
			}
		}
		return "error";
	}
	
	//编辑之后进行更新保存
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flag = userService.update(u);
		if(flag == false){
			request.setAttribute("error_message", "修改用户失败！");
			return "error";
		}
		return find();
	}
}
