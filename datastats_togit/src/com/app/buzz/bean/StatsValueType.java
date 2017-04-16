package com.app.buzz.bean;

/**
 * @title 统计值类型
 * @desc 统计值类型
 * @author nobleyd
 *
 */
public enum StatsValueType {
	/** 数值 */
	number("数值"), 
	/** 文本(注意文本类型只可以进行last统计方法。) */
	text("文本");

	private String title;

	private StatsValueType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
