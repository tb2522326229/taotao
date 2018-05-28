package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 查询所有商品分类生成前台页面要求的json数据
 */
public class CatResult implements Serializable{

	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
	
	
}
