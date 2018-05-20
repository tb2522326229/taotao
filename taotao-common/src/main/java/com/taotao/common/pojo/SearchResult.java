package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 前台用户返回查询后的商品列表和分页信息
 */
public class SearchResult implements Serializable{

	private long totalPages;// 商品总页数
	private long recordCount;// 商品总记录数
	private List<SearchItem> itemList;//商品列表
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
}
