package com.app.buzz.service;

import java.math.BigDecimal;
import java.util.List;

import com.app.buzz.bean.StatsCycle;
import com.app.buzz.entity.StatsData;
import com.app.service.BaseService;

public interface StatsDataService extends BaseService<StatsData, Long> {

	/** 查询 */
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime);

	/** 采集数据 */
	/**
	 * @title 某个统计项的某个时间点的数据
	 * @desc 某个统计项的某个时间点的数据
	 * @param StatsItemId
	 *            统计项Id
	 * @param dataTime
	 *            数据时间
	 * @param numberValue
	 *            数值类型的值
	 */
	public int acceptData(Long statsItemId, Long dataTime, BigDecimal numberValue, String textValue);

	/** 统计数据 */

	/**
	 * @title 按照指定周期统计某个统计项在某段时间范围内的数据
	 * @desc 按照指定周期统计某个统计项在某段时间范围内的数据
	 * @param StatsItemId
	 *            统计项Id
	 * @param statsCycle
	 *            统计周期
	 * @param divNumber
	 *            自定义周期
	 * @param startDate
	 *            开始时间范围
	 * @param endDate
	 *            结束时间范围
	 * @return 返回List,每个元素代表一个时间的数据
	 */
	public List<com.app.buzz.bean.StatsData> findLastNumberData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findLastTextData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findSumData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findMinData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findMaxData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findAverageData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

}
