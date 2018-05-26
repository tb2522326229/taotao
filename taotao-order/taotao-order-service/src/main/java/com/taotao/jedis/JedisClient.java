package com.taotao.jedis;

public interface JedisClient {

	/**
	 * 添加缓存
	 * @param key 被缓存数据的key
	 * @param value 被缓存数据的对象：需要把被转换的pojo转换成json字符串
	 * @return
	 */
	String set(String key, String value);
	
	/**
	 * 从缓存中获取该key对应的对象的json格式字符串
	 * @param key 要被获取数据的key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 判断该key是否存在
	 * @param key
	 * @return
	 */
	Boolean exists(String key);
	
	/**
	 * 设置key所对应对象的失效时间
	 * @param key 对象的key
	 * @param seconds 失效时间（单位：s）
	 * @return
	 */
	Long expire(String key, int seconds);
	
	/**
	 * 以秒为单位，返回给定 key 的剩余生存
	 * @param key
	 * @return
	 */
	Long ttl(String key);
	
	/**
	 * 对存储在指定key的数值执行原子的加1操作
	 * @param key
	 * @return
	 */
	Long incr(String key);
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	Long hset(String key, String field, String value);
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	String hget(String key, String field);
	
	/**
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	Long hdel(String key, String... field);
	
	Long del(String key);
}
