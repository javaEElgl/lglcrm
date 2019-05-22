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
 *������������UserAction�������Ϣ
 */
public class UserAction extends ActionSupport {
	private User u;
	private Page pager;   //���ڻ�ȡ��ҳ��ѯ��ʾ
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

	
	//��¼����
	public String login() {
		User user = userService.login(u.getUsername(), u.getPassword());
		// ��½�ɹ�
		if (user != null) {
			//ȡ����ɫid�����߽�ɫid�ҵ���ɫ����ֵ���û�
			int roleId=user.getRole().getRoleId();
			Role role=userService.find_RoleByID(roleId);
			user.setRole(role);
			//��ȡ��ǰ��¼�û����е�Ȩ�ޣ���Ȩ�޺͵�¼�û����浽session��
			List<Right> ri=userService.find_Right(roleId);
			Map<String, Object> session = ActionContext.getContext().getSession();
			//��session�б���user����
			session.put("user", user);
			//��session�б���Ȩ��
			session.put("rights", ri);
			return "login_success";
		} else {
			ActionContext.getContext().put("loginError", "�û����������");
			return "error";
		}
	}
	
	//�˳�����
	public String exit(){
		ServletActionContext.getRequest().getSession().invalidate();  //���session
		return "exit";
	}
	
	
	public String find(){
		//ɨ��һ�����µĽ�ɫ��
		List<Role> rlist = userService.find_Role();
		Map<String, Object> session = ActionContext.getContext().getSession();
		//�����н�ɫ������session����
		session.put("rolelist",rlist);
		//��ѯ���û���ҳ��ʾ
		pager = userService.splitPage(pager.getPageIndex(),pager.getPageSize());
		List<User> list = pager.getList();
		session.put("userlist",list);
		return "alluser";
	}
	
	//�����û������в�ѯ
	public String findName(){
		if("".equals(u.getUsername())){
			return this.find();
		}
		List<User> list = userService.find(u);
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("userlist",list);
		return "findName";
	}
	
	//ʵ����תЧ��
	public String toadd(){
		return "toadd";
	}
	
	//����û�
	public String add(){
		boolean flag = userService.add(u);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(flag == false){
			request.setAttribute("error_message", "�����û�ʧ�ܣ�");
			return "error";
		}
		return this.find();
	}
	
	//ajaxУ���û����Ƿ����
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
	
	
	//ɾ���û�
	public String del(){
		boolean flag = userService.del(u);
		HttpServletRequest request = ServletActionContext.getRequest();
		if(flag == false){
			request.setAttribute("error_message", "ɾ���û�ʧ�ܣ�");
			return "error";
		}
		return find();
	}
	
	//�༭�û��Ȼ�ȡҪ�༭�û���������Ϣ
	public String edit(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<User> list = (List<User>) session.get("userlist");
		//ѭ���ҵ����Ǳ༭��user����󶨵�request��
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
	
	//�༭֮����и��±���
	public String update(){
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flag = userService.update(u);
		if(flag == false){
			request.setAttribute("error_message", "�޸��û�ʧ�ܣ�");
			return "error";
		}
		return find();
	}
}
