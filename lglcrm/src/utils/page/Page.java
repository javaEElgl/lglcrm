//��ҳ��Ϣ��װ��
package utils.page;

/**
 * lgl
 * �����ר���������з�ҳ����
 */
import java.util.List;

public class Page {
	private List list;   //Ҫ��ҳ�ļ���
	private int count;   //�ܼ�¼��
	private int pages;   //��ҳ��
	private int pageIndex = 1;  //��ǰҳ�� ,Ĭ�ϵ�һ��ʾ��ҳ
	private int pageSize = 10;   //ÿҳ��ʾ���� ��Ĭ��ÿҳ��ʾ15��
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/*
	 * ����ҳ��
	 */
	public static int getLenght(int allRow,int pageSize){
		int lenght = (allRow%pageSize == 0)?(allRow/pageSize):(allRow/pageSize+1);
		return lenght;
		
	}
	/*
	 * ���ݴ����ҳ���ҳ�����ж�ҳ��Խ�磬����һ����ȷ��Χҳ��
	 */
	public static int gettrueIndex(int pageIndex,int pages){
		int trueIndex = 1;
		if(pageIndex < 1){  
			pageIndex = 1;
		}
		if(pageIndex > pages){
			pageIndex = pages;
		}
		trueIndex=pageIndex;
		return trueIndex;
	}
	
	/*
	 * ���㿪ʼ�±�
	 */
	public static int getOffset(int pageIndex,int pageSize){
		int offset = pageSize*(pageIndex-1);
		return offset;
		
	}
	
}
