package cust.pojo;

public class Servicer {
	private int ID;    //���������
	private String type;   //��������
	private String title;   //��Ҫ
	private String custname;   //�ͻ�
	private int status;  //����״̬      1�´��� 2�ѷ��� 
 	private String request;  //������������
	private String createBy;  //������
	private String createDate; //��������
	private String dueTo;   //�����ĳ��
	private String dueDate;  //����ʱ��
	private String deal;     //����������
	private String dealby;    //������
	private String dealDate;    //����ʱ��
	private String result;     //������
	private int satisfy;      //�����
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDueTo() {
		return dueTo;
	}
	public void setDueTo(String dueTo) {
		this.dueTo = dueTo;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	public String getDealby() {
		return dealby;
	}
	public void setDealby(String dealby) {
		this.dealby = dealby;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getSatisfy() {
		return satisfy;
	}
	public void setSatisfy(int satisfy) {
		this.satisfy = satisfy;
	}
	
}
