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
	private Page pager; // ���ڻ�ȡ��ҳ��ѯ��ʾ
	private Role r;
	private RoleService roleService;
	private RoleRightService rrService;
	Map<String, Object> Session;
	Map<String, Object> Request;

	private String[] rightCode = null; // ���ڻ�ȡҳ��Ȩ����Ϣ

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

	// --------������ʼ-------------------------
	// ��ѯȫ��
	public String find() {
		pager = roleService
				.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Role> list = pager.getList();
		Session.put("rolelist", list);
		return "success";
	}

	// ������ѯ
	public String findHQL() {
		List<Role> list = roleService.find(r);
		Session.put("rolelist", list);
		return "success";
	}

	public String add() {
		//��ӽ�ɫ
		boolean flag = roleService.add(r);
		if (flag) {
			//��ɫ��ӳɹ���Ϊ��ɫ���Ȩ��
			Role_Right rr = new Role_Right();
			rr.setRoleId(r.getRoleId());
//			System.out.println(Arrays.toString(rightCode));
			if (rightCode != null) {
				for (int i = 0; i < rightCode.length; i++) {
					//���ݾ��еĽ�ɫȨ�ޣ������ɫȨ�ޱ�
					int rid = Integer.parseInt(rightCode[i]); // תint
					rr.setRightId(rid);
					boolean flag1 = rrService.add(rr);
					if (flag1 == false) {
						Request.put("error_message",
								"������ɫ�ɹ���������Ȩδ�ɹ����뷵�ؽ�ɫ�б����±༭Ȩ�ޣ�");
						return "error";
					}
				}
				rightCode = null; // Ȩ���������
			}
			return this.find();
		}
		Request.put("error_message", "������ɫʧ�ܣ������ɫ���Ƿ��Ѿ����ڣ�");
		return "error";
	}

	//�����㴫�ݵ�id�ҵ���Ҫ�༭�Ľ�ɫ����Ȼ��󶨵�request��
	public String edit() {
		List<Role> list = (List<Role>) Session.get("rolelist");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoleId() == r.getRoleId()) {
				//���ݽ�ɫid�ҵ���ɫ��Ȩ��
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
		boolean flag = roleService.update(r); // ���޸Ľ�ɫ������ɹ��ͽ�����һ����
		if (flag) {
			Role_Right rr = new Role_Right();
			rr.setRoleId(r.getRoleId());
			rrService.del(rr); // ��ɾ������Ȩ�ޣ��ٸ���ҳ��ѡ�е�Ȩ��������Ȩ��
			if (rightCode != null) {
				for (int i = 0; i < rightCode.length; i++) {
					int rid = Integer.parseInt(rightCode[i]); // תint
					rr.setRightId(rid);
					boolean flag1 = rrService.add(rr);
					if (flag1 == false) {
						Request.put("error_message",
								"�޸Ľ�ɫ�ɹ���������Ȩδ�ɹ����뷵�ؽ�ɫ�б����±༭Ȩ�ޣ�");
						return "error";
					}
				}
				rightCode = null; // Ȩ���������
			}
			return this.find();
		}
		Request.put("error_message", "�޸Ľ�ɫʧ�ܣ������ɫ���Ƿ��Ѿ����ڣ�");
		return "error";
	}

	public String del() {
		boolean flag = roleService.del(r);
		if (flag == false) {
			Request.put("error_message", "ɾ����ɫʧ�ܣ�����ȡ���ý�ɫ����Ӧ���û���Ȩ�ޣ�");
			return "error";
		}
		return find();
	}

	public String toadd() {
		// ���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
}
