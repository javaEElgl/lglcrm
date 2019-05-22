package base.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import utils.page.Page;
import base.pojo.Product;
import base.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="ProductAction")
public class ProductAction extends ActionSupport implements RequestAware,SessionAware {
	Map<String,Object> Session;
    Map<String,Object> Request; 
    
    @Resource(name="productService")
    private ProductService productService;
    @Resource(name="pager")
    private Page pager;
    private String flag;   //传递标识
    private Product pr;
    
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
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public Product getPr() {
		return pr;
	}
	public void setPr(Product pr) {
		this.pr = pr;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	public String find(){
		pager = productService.splitPage(pager.getPageIndex(), pager.getPageSize());
		List<Product> list = pager.getList();
		Session.put("productlist",list);
		if(flag.equals("storage")){
			return "sto_succ";
		}
		return "pro_succ";
	}
	
	public String findHQL(){
		List<Product> list = productService.find(pr);
		if(flag.equals("storage")){
			return "sto_succ";
		}
		return "pro_succ";
	}
	
	public String add(){
		boolean flag = productService.add(pr);
		if(flag == false){
			Request.put("error_message", "新增产品，请仔细核对填写信息！");
			return "error";
		}
		return this.find();
	}
	
	public String del(){
		boolean flag = productService.del(pr);
		if(flag == false){
			Request.put("error_message", "删除产品失败，请重试！");
			return "error";
		}
		return this.find();
	}
	
	public String toadd(){
		return "add";
	}
}
