package cn.cs.core.util;

import java.util.*;

public class PageResult {
	
	//总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//总页数
	private int pageTotalCount;
	//页大小
	private int pageSize;
	//列表记录
	private List items;
	
	
	public PageResult() {
		
	}
	
	public PageResult(long totalCount, int pageNo,int pageSize, List items) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.items = items==null?new ArrayList():items;
		if(totalCount != 0)
		{
			this.pageNo = pageNo;
			int tmp = (int) (totalCount/pageSize);
			this.pageTotalCount = totalCount%pageSize == 0?tmp:tmp+1;
		}else{
			this.pageNo = 0;
		}
	}



	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
	
}
