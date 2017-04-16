package com.app.buzz.dao;

import java.math.BigDecimal;
import java.util.List;

import com.app.buzz.bean.StatsCycle;
import com.app.buzz.entity.StatsData;
import com.app.dao.BaseDao;

public interface StatsDataDao extends BaseDao<StatsData, Long> {

	/** 查询 */
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime);

	/** 采集数据 */

	/**
	 * @title 更新某个统计项的某个时间点的文本类型数据
	 * @desc 更新某个统计项的某个时间点的文本类型数据
	 * @param statsItemId
	 *            统计项Id
	 * @param dataTime
	 *            数据时间
	 * @param textValue
	 *            文本类型的值
	 * @return 返回更新成功数量(0->失败,1->成功)
	 */
	public int updateData(Long statsItemId, Long dataTime, String textValue);

	/**
	 * @title 更新某个统计项的某个时间点的数值类型数据
	 * @desc 更新某个统计项的某个时间点的数值类型数据
	 * @param statsItemId
	 *            统计项Id
	 * @param dataTime
	 *            数据时间
	 * @param numberValue
	 *            数值类型的值
	 * @return 返回更新成功数量(0->失败,1->成功)
	 */
	public int updateData(Long statsItemId, Long dataTime, BigDecimal numberValue);

	/**
	 * @title 累加某个统计项的某个时间点的数值类型数据
	 * @desc 累加某个统计项的某个时间点的数值类型数据
	 * @param statsItemId
	 *            统计项Id
	 * @param dataTime
	 *            数据时间
	 * @param numberValue
	 *            数值类型的值
	 * @return 返回更新成功数量(0->失败,1->成功)
	 */
	public int sumData(Long statsItemId, Long dataTime, BigDecimal numberValue);

	public int maxData(Long statsItemId, Long dataTime, BigDecimal numberValue);

	public int minData(Long statsItemId, Long dataTime, BigDecimal numberValue);

	public int avgData(Long statsItemId, Long dataTime, BigDecimal numberValue);

	/** 统计数据 */

	/**
	 * @title 按照指定周期统计某个统计项在某段时间范围内的数据
	 * @desc 按照指定周期统计某个统计项在某段时间范围内的数据
	 * @param statsItemId
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
	public List<com.app.buzz.bean.StatsData> findLastNumberData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findLastTextData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findSumData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findMinData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findMaxData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

	public List<com.app.buzz.bean.StatsData> findAverageData(Long statsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end);

}
