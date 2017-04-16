/*
 * Copyright 2005-2013 com.app. All rights reserved.
 * Support: http://www.com.app
 * License: http://www.com.app/license
 */
package com.app;

/**
 * 公共参数
 * 
 * @author APP TEAM
 * @version 3.0
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = //
	new String[] { "yyyy", //
			"yyyy-MM",//
			"yyyyMM", //
			"yyyy/MM", //
			"yyyy-MM-dd",//
			"yyyyMMdd", //
			"yyyy/MM/dd", //
			"yyyy-MM-dd HH:mm:ss",//
			"yyyyMMddHHmmss",//
			"yyyy/MM/dd HH:mm:ss"//
	};

	/** app.xml文件路径 */
	public static final String APP_XML_PATH = "/app.xml";

	/** app.properties文件路径 */
	public static final String APP_PROPERTIES_PATH = "/app.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}