package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 操作用户的后台service服务
 */
public interface UserService {

	/**
	 * 查询用户信息是否存在
	 * @param param 要查询的信息：username,email,phone
	 * @param type 每个信息对应的类型：1.username；2.email；3.phone
	 * @return
	 */
	public TaotaoResult checkData(String param,Integer type);
	
	/**
	 * 用户注册
	 * @param user 注册的信息
	 * @return
	 */
	public TaotaoResult register(TbUser user);
	
	/**
	 * 用户登录操作
	 * @param username 用户名
	 * @param password 密码（md5加密过的）
	 * @return
	 */
	public TaotaoResult login(String username,String password);
	
	/**
	 * 根据token查询用户信息
	 * @param token 用户的sessionid
	 * @return
	 */
	public TaotaoResult getUserByToken(String token);

	/**
	 * 安全退出
	 * @param token用户的sessionid
	 * @return
	 */
	public TaotaoResult logout(String token); 
}
