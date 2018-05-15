package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.utils.JsonUtils;
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CONENT}")
	private String INDEX_CONENT;
	@Override
	public EasyUIDataGridResult getItemList(Long categoryId, Integer pageNum, Integer pageSize) {
		//设置分页信息
		PageHelper.startPage(pageNum, pageSize);
		//执行查询
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult editContent(TbContent content) {
		content.setUpdated(new Date());
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(content.getId());
		contentMapper.updateByExampleSelective(content, example);
		// 同步缓存
		jedisClient.hdel(INDEX_CONENT, content.getCategoryId().toString());
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteBatchContent(List<Long> ids) {
		for (Long id : ids) {
			String contentCategoryId = contentMapper.selectByPrimaryKey(id)
					.getCategoryId().toString();
			jedisClient.hdel(INDEX_CONENT, contentCategoryId);
		}
		contentMapper.deleteBatchContent(ids);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult addContent(TbContent content) {
		//补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		// 同步缓存
		jedisClient.hdel(INDEX_CONENT, content.getCategoryId().toString());
		return TaotaoResult.ok();

	}

	@Override
	public List<TbContent> getContentList(Long categoryId) {
		try {
			// 查询redis缓存
			String json = jedisClient.hget(INDEX_CONENT, categoryId.toString());
			if(StringUtils.isNotBlank(json)){
				return JsonUtils.jsonToList(json, TbContent.class);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		try {
			// 添加进redis缓存
			jedisClient.hset(INDEX_CONENT, categoryId.toString(), JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
