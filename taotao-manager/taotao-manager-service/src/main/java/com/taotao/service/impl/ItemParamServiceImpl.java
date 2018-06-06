package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemParamAndCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamAndCat;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamAndCatMapper itemParamAndCatMapper;

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Override
	public EasyUIDataGridResult getItemParamList(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<TbItemParamAndCat> itemList = itemParamAndCatMapper.getItemParamList();
		PageInfo<TbItemParamAndCat> pageInfo = new PageInfo<TbItemParamAndCat>(itemList);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult saveItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		// 插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		jedisClient.set(ITEM_INFO + ":" + itemParam.getId()  + ":PARAM",  JsonUtils.objectToJson(itemParam));
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult queryItemParam(long catId) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(catId);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteBatch(List<Long> ids) {
		itemParamMapper.deleteBatch(ids);
		for (Long id : ids) {
			jedisClient.del(ITEM_INFO + ":" + id  + ":PARAM");
		}
		return TaotaoResult.ok();
	}

}
