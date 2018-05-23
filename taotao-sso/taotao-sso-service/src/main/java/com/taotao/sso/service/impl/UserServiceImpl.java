package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Override
	public TaotaoResult checkData(String param, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if(type == 1){ // 验证的是用户名
			criteria.andUsernameEqualTo(param);
		}else if(type == 2){// 验证的是邮箱
			criteria.andEmailEqualTo(param);
		}else if(type == 3){// 验证的是手机号
			criteria.andPhoneEqualTo(param);
		}else{
			return TaotaoResult.build(400, "输入参数不合法");
		}
		List<TbUser> userList = userMapper.selectByExample(example);
		// 如果该用户存在
		if(userList != null && userList.size() > 0){
			return TaotaoResult.ok(400,"该字段已被注册，请更换",false);
		}else{
			return TaotaoResult.ok(true);
		}
	}
	
	@Override
	public TaotaoResult register(TbUser user) {
		//检查数据的有效性
		if (StringUtils.isBlank(user.getUsername())) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		//判断用户名是否重复
		TaotaoResult taotaoResult = checkData(user.getUsername(), 1);
		if (!(boolean) taotaoResult.getData()) {
			return TaotaoResult.build(400, "用户名重复");
		}
		//判断密码是否为空
		if (StringUtils.isBlank(user.getPassword())) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		if (StringUtils.isNotBlank(user.getPhone())) {
			//是否重复校验
			taotaoResult = checkData(user.getPhone(), 2);
			if (!(boolean) taotaoResult.getData()) {
				return TaotaoResult.build(400, "电话号码重复");
			}
		}
		//如果email不为空的话进行是否重复校验
		if (StringUtils.isNotBlank(user.getEmail())) {
			//是否重复校验
			taotaoResult = checkData(user.getEmail(), 3);
			if (!(boolean) taotaoResult.getData()) {
				return TaotaoResult.build(400, "email重复");
			}
		}
		//补全pojo的属性
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//密码要进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//插入数据
		userMapper.insert(user);
		//返回注册成功
		return TaotaoResult.ok();
	}
}
