//分页信息封装类
package utils.page;

/**
 * lgl
 * 这个类专门用来进行分页操作
 */
import java.util.List;

public class Page {
	private List list;   //要分页的集合
	private int count;   //总记录数
	private int pages;   //总页数
	private int pageIndex = 1;  //当前页码 ,默认第一显示首页
	private int pageSize = 10;   //每页显示条数 ，默认每页显示15条
	
	
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
	 * 计算页数
	 */
	public static int getLenght(int allRow,int pageSize){
		int lenght = (allRow%pageSize == 0)?(allRow/pageSize):(allRow/pageSize+1);
		return lenght;
		
	}
	/*
	 * 根据传入的页码和页数，判断页码越界，返回一个正确范围页码
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
	 * 计算开始下标
	 */
	public static int getOffset(int pageIndex,int pageSize){
		int offset = pageSize*(pageIndex-1);
		return offset;
		
	}
	
}
