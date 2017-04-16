package com.app.buzz.dao.impl;

import org.springframework.stereotype.Repository;

import com.app.buzz.dao.StatsItemDao;
import com.app.buzz.entity.StatsItem;
import com.app.dao.impl.BaseDaoImpl;

@Repository("statsItemDaoImpl")
public class StatsItemDaoImpl extends BaseDaoImpl<StatsItem, Long> implements StatsItemDao {

}
