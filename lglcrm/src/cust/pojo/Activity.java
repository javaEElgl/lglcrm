package cust.pojo;

public class Activity {
	private int ID;				//������¼���
	private String date;		//������¼ʱ��	
	private String place;		//������¼�ص�
	private String title;		//������¼��Ҫ
	private String desc;		//��ϸ��Ϣ
	private Customer customer;	//ȡ�ͻ���Ϣ���
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
