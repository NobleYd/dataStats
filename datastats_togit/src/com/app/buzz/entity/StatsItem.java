package com.app.buzz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.app.buzz.bean.StatsCycle;
import com.app.buzz.bean.StatsMethod;
import com.app.buzz.bean.StatsValueType;
import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @title 统计项目
 * @desc 统计项目
 * @author nobleyd
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "partnerId", "title", "subTitle" }) })
/** 会员ID-title-subTitle唯一标识了一个统计项 */
public class StatsItem extends BaseEntity {

	private static final long serialVersionUID = 6207651119543191680L;

	public static final String SUBTITLE_DEFAULT = "SUBTITLE_DEFAULT";
	public static final String SUB_SHOW_TITLE_DEFAULT = "";

	/** 创建此统计项的会员Id */
	@JsonProperty
	@Column(nullable = false)
	private long partnerId = -1L;

	/** 用于标识统计项(英文) */
	@JsonProperty
	@Column(nullable = false, length = 200)
	private String title;

	/** 用于标识子统计项(英文) */
	@JsonProperty
	private String subTitle = SUBTITLE_DEFAULT;

	/** 统计项显示名称 */
	@JsonProperty
	@Column(nullable = false, length = 200)
	private String showTitle;

	/** 子统计项显示名称 */
	@JsonProperty
	private String subShowTitle = SUB_SHOW_TITLE_DEFAULT;

	/** 统计值类型 */
	@JsonProperty
	@Column(nullable = false)
	private StatsValueType statsValueType = StatsValueType.number;

	/** 统计周期 */
	@JsonProperty
	@Column(nullable = false)
	private StatsCycle statsCycle = StatsCycle.second;

	/** 当使用自定义的统计周期时，使用divNumber决定周期，单位是数据点。 */
	@JsonProperty
	@Column(nullable = false)
	private Long divNumber = 1L;

	/** 统计方法 */
	@JsonProperty
	@Column(nullable = false)
	private StatsMethod statsMethod = StatsMethod.sum;

	/** getters and setters */

	public long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getSubShowTitle() {
		return subShowTitle;
	}

	public void setSubShowTitle(String subShowTitle) {
		this.subShowTitle = subShowTitle;
	}

	public StatsValueType getStatsValueType() {
		return statsValueType;
	}

	public void setStatsValueType(StatsValueType statsValueType) {
		this.statsValueType = statsValueType;
	}

	public StatsCycle getStatsCycle() {
		return statsCycle;
	}

	public void setStatsCycle(StatsCycle statsCycle) {
		this.statsCycle = statsCycle;
	}

	public Long getDivNumber() {
		return divNumber;
	}

	public void setDivNumber(Long divNumber) {
		this.divNumber = divNumber;
	}

	public StatsMethod getStatsMethod() {
		return statsMethod;
	}

	public void setStatsMethod(StatsMethod statsMethod) {
		this.statsMethod = statsMethod;
	}

}
