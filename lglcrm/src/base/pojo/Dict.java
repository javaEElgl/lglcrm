package base.pojo;

public class Dict {
	private int ID;            //���
	private String type;	  //����	
	private String item;		//��Ŀ
	private String value;		//ֵ
	private String isedit;		//�Ƿ�ɱ༭
	
	
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
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsedit() {
		return isedit;
	}
	public void setIsedit(String isedit) {
		this.isedit = isedit;
	}
	
}
