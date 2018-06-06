package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.utils.FastDFSClient;
import com.taotao.utils.JsonUtils;

@Controller
public class PictureController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping(value = "/pic/upload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile) {
		try {
			// 获取被上传图片的图片名称
			String filename = uploadFile.getOriginalFilename();
			// 获取被上传图片的扩展名（jpg，没有.）
			String extName = filename.substring(filename.lastIndexOf(".") + 1);
			// 创建fastDfs对象，将配置文件的位置指定好
			FastDFSClient fastdfsClient = new FastDFSClient("classpath:resource/client.conf");
			// 执行上传操作并返回上传图片的url地址
			String url = fastdfsClient.uploadFile(uploadFile.getBytes(), extName);
			// 拼接url使图片的url得以完整
			url = IMAGE_SERVER_URL + url;
			// 创建map对象来封装上传成功后的信息
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			// 为了避免浏览器对kindeditor不兼容，把封装的map转换为字符串返回给前台页面
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			// 创建map对象来封装上传失败后的信息
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			// 为了避免浏览器对kindeditor不兼容，把封装的map转换为字符串返回给前台页面
			return JsonUtils.objectToJson(result);
		}
	}
}
