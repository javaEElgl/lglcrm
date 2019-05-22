//���ۻ���
package sale.pojo;

import java.util.HashSet;
import java.util.Set;


public class Sale {
	private int id;     //���
	private String source;  //������Դ
	private String name;   //�ͻ�����
	private String contact;  //��ϵ��
	private String phone;      //��ϵ�绰
	private String title;   //��Ҫ
	private String rate;    //�ɹ���
	private String desc;    //��������
	private String createName;   //���ᴴ���˱��     
	private String createTime;  //����ʱ��
	private String dueName;     //��ָ���˱��
	private String dueTime;  //ָ��ʱ��
	private int status;      //״̬
	private Set<Plan> plan = new HashSet<Plan>();
	
	
	public Sale() {
	}


	public Sale(int id, String source, String name, String contact,
			String phone, String title, String rate, String desc,
			String createName, String createTime, String dueName,
			String dueTime, int status, Set<Plan> plan) {
		this.id = id;
		this.source = source;
		this.name = name;
		this.contact = contact;
		this.phone = phone;
		this.title = title;
		this.rate = rate;
		this.desc = desc;
		this.createName = createName;
		this.createTime = createTime;
		this.dueName = dueName;
		this.dueTime = dueTime;
		this.status = status;
		this.plan = plan;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getRate() {
		return rate;
	}


	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getCreateName() {
		return createName;
	}


	public void setCreateName(String createName) {
		this.createName = createName;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getDueName() {
		return dueName;
	}


	public void setDueName(String dueName) {
		this.dueName = dueName;
	}


	public String getDueTime() {
		return dueTime;
	}


	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Set<Plan> getPlan() {
		return plan;
	}


	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}

}
