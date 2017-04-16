package com.app.buzz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.app.buzz.dao.StatsItemDao;
import com.app.buzz.entity.StatsItem;
import com.app.buzz.service.StatsItemService;
import com.app.service.impl.BaseServiceImpl;

@Service("statsItemServiceImpl")
public class StatsItemServiceImpl extends BaseServiceImpl<StatsItem, Long> implements StatsItemService {

	@Resource(name="statsItemDaoImpl")
	private StatsItemDao statsItemDao;
	
	@Resource(name="statsItemDaoImpl")
	public void setBaseDao(StatsItemDao statsItemDao) {
		super.setBaseDao(statsItemDao);
	}
	
}
