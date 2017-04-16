package com.app.buzz.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.bean.StatsCycle;
import com.app.buzz.bean.StatsMethod;
import com.app.buzz.bean.StatsValueType;
import com.app.buzz.dao.StatsDataDao;
import com.app.buzz.dao.StatsItemDao;
import com.app.buzz.entity.StatsData;
import com.app.buzz.entity.StatsItem;
import com.app.buzz.service.StatsDataService;
import com.app.service.impl.BaseServiceImpl;

@Service("statsDataServiceImpl")
public class StatsDataServiceImpl extends BaseServiceImpl<StatsData, Long> implements StatsDataService {

	@Resource(name = "statsDataDaoImpl")
	private StatsDataDao statsDataDao;

	@Resource(name = "statsItemDaoImpl")
	private StatsItemDao statsItemDao;

	@Resource(name = "statsDataDaoImpl")
	public void setBaseDao(StatsDataDao statsDataDao) {
		super.setBaseDao(statsDataDao);
	}

	@Override
	public StatsData findByStatsItemIdAndDataTime(Long statsItemId, Long dataTime) {
		return statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
	}

	/** 采集数据 */
	@Override
	public int acceptData(Long statsItemId, Long dataTime, BigDecimal numberValue, String textValue) {
		StatsItem statsItem = statsItemDao.find(statsItemId);
		if (statsItem == null)
			return -1;
		if (StatsValueType.number == statsItem.getStatsValueType()) {
			int updateNumber = 0;
			if (StatsMethod.last == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.updateData(statsItemId, dataTime, numberValue);
			} else if (StatsMethod.sum == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.sumData(statsItemId, dataTime, numberValue);
			} else if (StatsMethod.max == statsItem.getStatsMethod()) {
				StatsData statsData = statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
				if (statsData != null) {
					statsDataDao.maxData(statsItemId, dataTime, numberValue);
					updateNumber = 1;
				}
			} else if (StatsMethod.min == statsItem.getStatsMethod()) {
				StatsData statsData = statsDataDao.findByStatsItemIdAndDataTime(statsItemId, dataTime);
				if (statsData != null) {
					statsDataDao.minData(statsItemId, dataTime, numberValue);
					updateNumber = 1;
				}

			} else if (StatsMethod.avg == statsItem.getStatsMethod()) {
				updateNumber = statsDataDao.avgData(statsItemId, dataTime, numberValue);
			}
			if (updateNumber <= 0) {
				super.save(new StatsData(statsItem, dataTime, numberValue, null));
				return 2;
			} else {
				return 1;
			}
		} else {
			// statsItem.getStatsValueType() == StatsValueType.text
			int updateNumber = statsDataDao.updateData(statsItemId, dataTime, textValue);
			if (updateNumber <= 0) {
				super.save(new StatsData(statsItem, dataTime, null, textValue));
				return 2;
			} else {
				return 1;
			}
		}
	}

	/** 统计数据 */
	@Override
	public List<com.app.buzz.bean.StatsData> findLastNumberData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findLastNumberData(StatsItemId, statsCycle, divNumber, start, end);
	}

	@Override
	public List<com.app.buzz.bean.StatsData> findLastTextData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findLastTextData(StatsItemId, statsCycle, divNumber, start, end);
	}

	@Override
	public List<com.app.buzz.bean.StatsData> findSumData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findSumData(StatsItemId, statsCycle, divNumber, start, end);
	}

	@Override
	public List<com.app.buzz.bean.StatsData> findMinData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findMinData(StatsItemId, statsCycle, divNumber, start, end);
	}

	@Override
	public List<com.app.buzz.bean.StatsData> findMaxData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findMaxData(StatsItemId, statsCycle, divNumber, start, end);
	}

	@Override
	public List<com.app.buzz.bean.StatsData> findAverageData(Long StatsItemId, StatsCycle statsCycle, Long divNumber, Long start, Long end) {
		return statsDataDao.findAverageData(StatsItemId, statsCycle, divNumber, start, end);
	}

}
