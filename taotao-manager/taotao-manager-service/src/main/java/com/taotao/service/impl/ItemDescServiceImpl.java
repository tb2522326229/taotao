package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemDescExample.Criteria;
import com.taotao.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Override
	public TaotaoResult getItemDesc(Long id) {
		TbItemDescExample example = new TbItemDescExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		List<TbItemDesc> itemDesc = itemDescMapper.selectByExampleWithBLOBs(example);
		for (TbItemDesc tbItemDesc : itemDesc) {
			System.out.println(tbItemDesc.getItemDesc());
		}
		return TaotaoResult.ok(itemDesc.get(0));
	}
}
