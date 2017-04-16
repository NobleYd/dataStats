package com.app.buzz.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.app.entity.BaseEntity;

/**
 * @title 统计项数据
 * @desc 统计项数据
 * @author nobleyd
 *
 */
@Entity
public class StatsData extends BaseEntity {
	private static final long serialVersionUID = 8446301055940889002L;

	/** 此统计数据对应的统计项 */
	private StatsItem statsItem;

	/** 此统计数据的时间 */
	private Long dataTime;

	/** 此统计数据的数值类型的值 */
	private BigDecimal numberValue;

	/** 此统计数据的文本类型的值 */
	@Column(length = 200)
	private String textValue;

	/** 此统计值数据个数(在平均值统计方法中会用到这个字段) */
	private Long statsCount = 1L;

	/** constructors */
	public StatsData() {
		super();
	}

	public StatsData(StatsItem statsItem, Long dataTime, BigDecimal numberValue, String textValue) {
		super();
		this.statsItem = statsItem;
		this.dataTime = dataTime;
		this.numberValue = numberValue;
		this.textValue = textValue;
	}

	/** getters and setters */
	@ManyToOne
	public StatsItem getStatsItem() {
		return statsItem;
	}

	public void setStatsItem(StatsItem statsItem) {
		this.statsItem = statsItem;
	}

	public Long getDataTime() {
		return dataTime;
	}

	public void setDataTime(Long dataTime) {
		this.dataTime = dataTime;
	}

	public BigDecimal getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(BigDecimal numberValue) {
		this.numberValue = numberValue;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public Long getStatsCount() {
		return statsCount;
	}

	public void setStatsCount(Long statsCount) {
		this.statsCount = statsCount;
	}

}
