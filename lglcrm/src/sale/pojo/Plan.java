//�ͻ������ƻ�
package sale.pojo;

public class Plan {
	private int id;   //�ƻ����
	private String date;  //����
	private String todo;  //�ƻ���Ŀ
	private String result;  //ִ�н��
	private Sale sale;  //���ۻ���
	
	
	public Plan() {
	}

	public Plan(int id, String date, String todo, String result, Sale sale) {
		this.id = id;
		this.date = date;
		this.todo = todo;
		this.result = result;
		this.sale = sale;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getTodo() {
		return todo;
	}


	public void setTodo(String todo) {
		this.todo = todo;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public Sale getSale() {
		return sale;
	}


	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
}
