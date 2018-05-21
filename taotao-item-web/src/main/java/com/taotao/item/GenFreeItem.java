package com.taotao.item;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * 把要在前台页面展示的商品详细信息封装到此类中，
 * 作为生成模板的数据，item.ftl中${}里的属性就是这个类中的属性
 * @author Lenovo
 *
 */
public class GenFreeItem {
	private TbItem tbItem;
	private TbItemDesc tbItemDesc;
	private Item item;
	private TbItemParamItem tbItemParamItem;
	
	public TbItemParamItem getTbItemParamItem() {
		return tbItemParamItem;
	}
	public void setTbItemParamItem(TbItemParamItem tbItemParamItem) {
		this.tbItemParamItem = tbItemParamItem;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public TbItem getTbItem() {
		return tbItem;
	}
	public void setTbItem(TbItem tbItem) {
		this.tbItem = tbItem;
	}
	public TbItemDesc getTbItemDesc() {
		return tbItemDesc;
	}
	public void setTbItemDesc(TbItemDesc tbItemDesc) {
		this.tbItemDesc = tbItemDesc;
	}
	
}
