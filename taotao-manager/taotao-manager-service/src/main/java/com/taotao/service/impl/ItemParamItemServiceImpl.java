package com.taotao.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper paramItemMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${TIEM_EXPIRE}")
	private Integer TIEM_EXPIRE;
	@Override
	public TbItemParamItem getParamById(Long itemId) {
		//查询数据库之前先查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO + ":" + itemId  + ":PARAM");
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成pojo
				TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return itemParamItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> itemParamItems = paramItemMapper.selectByExample(example);
		if(itemParamItems != null && itemParamItems.size() > 0){
			TbItemParamItem itemParamItem = itemParamItems.get(0);
			try {
				//把查询结果添加到缓存
				jedisClient.set(ITEM_INFO + ":" + itemId  + ":PARAM", JsonUtils.objectToJson(itemParamItem));
				//设置过期时间，提高缓存的利用率
				jedisClient.expire(ITEM_INFO + ":" + itemId  + ":DESC", TIEM_EXPIRE);
				return itemParamItems.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
