package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * 添加solr索引库的搜索域
 * 1.后台查询后返回的商品列表所包含的属性
 * 2.后台搜索时用到的商品信息（此处item不是明细，其含义是product）
 * 3.也可以是前台搜索商品后返回的属性之一
 */
public class SearchItem implements Serializable {

	private String id;
	private String title;
	private String sell_point;
	private long price;
	private String image;
	private String category_name;
	private String item_desc;
	private String item_param_item;
	public String getItem_param_item() {
		return item_param_item;
	}
	public void setItem_param_item(String item_param_item) {
		this.item_param_item = item_param_item;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	
	
}
