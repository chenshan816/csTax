package cn.cs.core.action;

import java.util.List;

import cn.cs.core.util.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	
	protected List<String> selectedRow;
	//分页对象
	private PageResult pageResult;
	private int pageNo;
	private int pageSize;
	
	
	public List<String> getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(List<String> selectedRow) {
		this.selectedRow = selectedRow;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		if(pageSize == 0)
			pageSize =2;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
