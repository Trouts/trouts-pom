package cn.trouts.framework.context;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

/**
 * 查询条件顶层接口
 * 
 * @author jinhu
 *
 */
public abstract class TroutsCriteria {
	protected Integer totalRecord;// 总的记录数
	protected Integer page = 0;// 页码
	protected Integer pageSize = 20;// 一页的大小

	protected Sort.Direction direction ;
	protected String[] sortFields;
	
	private ExampleMatcher criteriaMatcher;

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getPage() {
		if (page == null || page < 0) {
			page = 0;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Sort.Direction getDirection() {
		return direction;
	}

	public void setDirection(Sort.Direction direction) {
		this.direction = direction;
	}

	public String[] getSortFields() {
		return sortFields;
	}

	public void setSortFields(String[] sortFields) {
		this.sortFields = sortFields;
	}

	public ExampleMatcher getCriteriaMatcher() {
		return criteriaMatcher;
	}

	public void setCriteriaMatcher(ExampleMatcher criteriaMatcher) {
		this.criteriaMatcher = criteriaMatcher;
	}
	
	

}
