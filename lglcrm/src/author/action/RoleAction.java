package author.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import utils.page.Page;
import author.pojo.Role;
import author.pojo.Role_Right;
import author.service.RoleRightService;
import author.service.RoleService;

import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport implements RequestAware,
		SessionAware {
	private Page pager; // 用于获取分页查询显示
	private Role r;
	private RoleService roleService;
	private RoleRightService rrService;
	Map<String, Object> Session;
	Map<String, Object> Request;

	private String[] rightCode = null; // 用于获取页面权限信息

	public Page getPager() {
		return pager;
	}

	public void setPager(Page pager) {
		this.pager = pager;
	}

	public Role getR() {
		return r;
	}

	public void setR(Role r) {
		this.r = r;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleRightService getRrService() {
		return rrService;
	}

	public void setRrService(RoleRightService rrService) {
		this.rrService = rrService;
	}

	public Map<String, Object> getSession() {
		return Session;
	}

	public void setSession(Map<String, Object> session) {
		Session = session;
	}

	public Map<String, Object> getRequest() {
		return Request;
	}

	public void setRequest(Map<String, Object> request) {
		Request = request;
	}

	public String[] getRightCode() {
		return rightCode;
	}

	public void setRightCode(String[] rightCode) {
		this.rightCode = rightCode;
	}

	// --------方法开始-------------------------
	// 查询全部
	public String find() {
		pager = roleService
				.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Role> list = pager.getList();
		Session.put("rolelist", list);
		return "success";
	}

	// 条件查询
	public String findHQL() {
		List<Role> list = roleService.find(r);
		Session.put("rolelist", list);
		return "success";
	}

	public String add() {
		//添加角色
		boolean flag = roleService.add(r);
		if (flag) {
			//角色添加成功后为角色添加权限
			Role_Right rr = new Role_Right();
			rr.setRoleId(r.getRoleId());
//			System.out.println(Arrays.toString(rightCode));
			if (rightCode != null) {
				for (int i = 0; i < rightCode.length; i++) {
					//根据具有的角色权限，保存角色权限表
					int rid = Integer.parseInt(rightCode[i]); // 转int
					rr.setRightId(rid);
					boolean flag1 = rrService.add(rr);
					if (flag1 == false) {
						Request.put("error_message",
								"新增角色成功，部分授权未成功，请返回角色列表重新编辑权限！");
						return "error";
					}
				}
				rightCode = null; // 权限数组清空
			}
			return this.find();
		}
		Request.put("error_message", "新增角色失败，请检查角色名是否已经存在！");
		return "error";
	}

	//根据你传递的id找到你要编辑的角色对象，然后绑定到request中
	public String edit() {
		List<Role> list = (List<Role>) Session.get("rolelist");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoleId() == r.getRoleId()) {
				//根据角色id找到角色的权限
				List<Role_Right> rlist= rrService.find(r.getRoleId());
				String right="";
				for(int n=1;n<=18;n++){
					for(int j=0;j<rlist.size();j++){
						if(rlist.get(j).getRightId()==n){
							right+="yes-";
							break;
						}
						if(j==rlist.size()-1){
							right+="no-";
						}
					}
				}
				Request.put("right", right);
//				System.out.println(right);
				Request.put("editrole", list.get(i));
				return "edit";
			}
		}
		return "error";
	}

	public String update() {
		boolean flag = roleService.update(r); // 先修改角色，如果成功就进入下一步。
		if (flag) {
			Role_Right rr = new Role_Right();
			rr.setRoleId(r.getRoleId());
			rrService.del(rr); // 先删除所有权限，再根据页面选中的权限重新授权。
			if (rightCode != null) {
				for (int i = 0; i < rightCode.length; i++) {
					int rid = Integer.parseInt(rightCode[i]); // 转int
					rr.setRightId(rid);
					boolean flag1 = rrService.add(rr);
					if (flag1 == false) {
						Request.put("error_message",
								"修改角色成功，部分授权未成功，请返回角色列表重新编辑权限！");
						return "error";
					}
				}
				rightCode = null; // 权限数组清空
			}
			return this.find();
		}
		Request.put("error_message", "修改角色失败，请检查角色名是否已经存在！");
		return "error";
	}

	public String del() {
		boolean flag = roleService.del(r);
		if (flag == false) {
			Request.put("error_message", "删除角色失败，请先取消该角色所对应的用户和权限！");
			return "error";
		}
		return find();
	}

	public String toadd() {
		// 添加页面中转，否则偶尔会出现界面css 和js失效问题
		return "add";
	}
}
