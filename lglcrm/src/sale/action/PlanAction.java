package sale.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import sale.pojo.Plan;
import sale.pojo.Sale;
import sale.service.PlanService;
import sale.service.SaleService;
import utils.page.Page;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="PlanAction")
public class PlanAction extends ActionSupport implements SessionAware,RequestAware{
	Map<String,Object> Session;
    Map<String,Object> Request; 
    Map<String,Object> Application;
    
    @Resource(name="pager")
    private Page pager;
    @Resource(name="planService")
    private PlanService planService;
    @Resource(name="saleService")
    private SaleService saleService;
    
    private Plan p;
    private Sale s;
    
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
	
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public PlanService getPlanService() {
		return planService;
	}
	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}
	public SaleService getSaleService() {
		return saleService;
	}
	public void setSaleService(SaleService saleService) {
		this.saleService = saleService;
	}
	public Plan getP() {
		return p;
	}
	public void setP(Plan p) {
		this.p = p;
	}
	public Sale getS() {
		return s;
	}
	public void setS(Sale s) {
		this.s = s;
	}
//-------������ʼ------------------
	public String find(){
		pager = saleService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Sale> list = pager.getList();
		Session.put("salelist",list);
		return "success";
	}
	
	public String findHQL(){
		List<Sale> list = saleService.find(s);
		Session.put("salelist",list);
		return "success";
	}
	
	public String add(){
		s.setStatus(3);  //���ô�Sale״̬Ϊ 3���ƶ��ƻ�
		saleService.update(s);  
		p.setSale(s);   //��s����plan�е����
		boolean flag = planService.add(p);
		if(flag == false){
			Request.put("error_message", "��Ӽƻ���Ϣ����");
			return "error";
		}
		return this.zdjh();
	}
	public String del(){
		boolean flag = planService.del(p);
		if(flag == false){
			Request.put("error_message", "ɾ���ƻ���Ϣ����");
			return "error";
		}
		return this.zdjh();
	}
	
	public String update(){
		boolean flag = planService.update(p);
		if(flag == false){
			Request.put("error_message", "�޸ļƻ���Ϣ����");
			return "error";
		}
		return this.zdjh();
	}
	public String saveresult(){
		boolean flag = planService.update(p);
		if(flag == false){
			Request.put("error_message", "��������Ϣ����");
			return "error";
		}
		return this.execute();
	}
	public String zdjh(){
		List<Sale> list = (List<Sale>) Session.get("salelist");
		for (int i = 0; i < list.size(); i++) {
			if(s.getId() == list.get(i).getId()){
				s = list.get(i);
				Request.put("editsale",s);
			}
		}
		//��ѯ֮ǰ�еļƻ�
		List<Plan> plans = planService.find(s);
		Request.put("planlist",plans);
		if(s.getStatus() !=2 && s.getStatus() !=3){
			Request.put("error_message", "��ǰ���ۻ��ᡮδָ�ɡ������ڿ����С���");
			return "error";
		}
		return "zdjh";
	}
	public String execute(){
		this.zdjh();
		if(s.getStatus() !=3 && s.getStatus() !=4){
			Request.put("error_message", "��ǰ���ۻ��ᡮδָ�ɡ��򡮻�δ�ƶ������ƻ�����");
			return "error";
		}
		s.setStatus(4);  //�������ۻ���״̬Ϊ4 ������
		saleService.update(s); //�޸����sale
		//----------
		return "execute";
	}
	public String read(){
		this.zdjh();
		return "read";
	}
	public String over(){
		saleService.update(s);
		return find();
	}
	
	public String toadd(){
		//���ҳ����ת������ż������ֽ���css ��jsʧЧ����
		return "add";
	}
    
}
