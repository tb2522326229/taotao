package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Value("${USER_TOKEN}")
	private String USER_TOKEN;
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public TaotaoResult checkData(@PathVariable String param,@PathVariable Integer type){
		return userService.checkData(param, type);
	}
	
	@RequestMapping(value = "/user/register" ,method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user){
		return userService.register(user);
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username,String password,HttpServletRequest request,HttpServletResponse response){
		TaotaoResult result = userService.login(username,password);
		if(result.getStatus() == 200){
			// 把用户的token信息写入cookie
			CookieUtils.setCookie(request, response, USER_TOKEN, result.getData().toString());
			return result;
		}else{
			return TaotaoResult.ok(result.getMsg());
		}
	}
	
	/*@RequestMapping(value="/user/token/{token}", method=RequestMethod.GET, 
	//指定返回响应数据的content-type
	produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable String token, String callback) {
	TaotaoResult result = userService.getUserByToken(token);
	//判断是否为jsonp请求
	if (StringUtils.isNotBlank(callback)) {
		return callback + "(" + JsonUtils.objectToJson(result) + ");";
	}
	return JsonUtils.objectToJson(result);
	}*/
	//jsonp的第二种方法，spring4.1以上版本使用
	@RequestMapping(value="/user/token/{token}", method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
	TaotaoResult result = userService.getUserByToken(token);
	//判断是否为jsonp请求
	if (StringUtils.isNotBlank(callback)) {
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		//设置回调方法
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	return result;
}
	
	@RequestMapping(value = "/user/logout/{token}", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult logout(@PathVariable String token){
		return userService.logout(token);
	}
	
}
