package com.app.util;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.OperationTimeoutException;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;
import net.spy.memcached.internal.OperationFuture;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MemcachedCache {

	protected static Log log = LogFactory.getLog(MemcachedCache.class);

	private MemcachedClient memcachedClient;

	private ObjectMapper mapper = new ObjectMapper();

	public void set(String key, String value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		} else {
			memcachedClient.set(key, 0, value);
			return;
		}
	}

	public void set(String key, String value, int expiry) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		} else {
			memcachedClient.set(key, expiry, value);
			return;
		}
	}

	public void set(String key, Object value, int expiry) {
		String sv = null;
		try {
			sv = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.warn("", e);
		}
		set(key, sv, expiry);
	}

	public <T> T get(String key, Class<T> valueType) {
		String value = get(key);
		if (value == null)
			return null;
		try {
			return mapper.readValue(value, valueType);
		} catch (Exception e) {
			log.warn("", e);
			return null;
		}
	}

	public String get(String key) {
		if (StringUtils.isEmpty(key))
			return null;
		String o;
		try {
			o = (String) memcachedClient.get(key);
		} catch (OperationTimeoutException e) {
			o = (String) memcachedClient.get(key);
		}
		return o;
	}

	public void delete(String key) {
		if (StringUtils.isEmpty(key)) {
			return;
		} else {
			memcachedClient.delete(key);
			return;
		}
	}

	public boolean exists(String key) {
		if (StringUtils.isEmpty(key))
			return false;
		else
			return memcachedClient.get(key) != null;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public static void main(String[] args) {

		// final String host = "448df95937f411e4.m.cnqdalicm9pub001.ocs.aliyuncs.com";// 控制台上的“内网地址”

		final String host = "127.0.0.1";// 控制台上的“内网地址”

		final String port = "11211"; // 默认端口 11211，不用改

		final String username = "448df95937f411e4";// 控制台上的“访问账号”

		final String password = "ab40_b92e";// 邮件或短信中提供的“密码”

		MemcachedClient cache = null;

		try {

			AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(username, password));

			cache = new MemcachedClient(

			new ConnectionFactoryBuilder().setProtocol(Protocol.BINARY)

			// .setAuthDescriptor(ad)

					.build(),

			AddrUtil.getAddresses(host + ":" + port));

			System.out.println("OCS Sample Code");

			// 向OCS中存一个key为"ocs"的数据，便于后面验证读取数据

			OperationFuture future = cache.set("ocs", 1000, " Open Cache Service,  from www.Aliyun.com");

			// 向OCS中存若干个数据，随后可以在OCS控制台监控上看到统计信息

			for (int i = 0; i < 10; i++) {

				String key = "key-" + i;

				String value = "value-" + i;

				// 执行set操作，向缓存中存数据

				cache.set(key, 1000, value);

			}

			System.out.println("Set操作完成!");

			future.get(); // 确保之前(mc.set())操作已经结束

			// 执行get操作，从缓存中读数据,读取key为"ocs"的数据

			System.out.println("Get操作:" + cache.get("ocs"));

		} catch (IOException e) {

			e.printStackTrace();

		} catch (InterruptedException e) {

			e.printStackTrace();

		} catch (ExecutionException e) {

			e.printStackTrace();

		}

		if (cache != null) {

			cache.shutdown();

		}

	}

}
