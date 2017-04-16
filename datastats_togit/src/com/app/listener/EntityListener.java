/*
 * Copyright 2005-2013 com.app. All rights reserved.
 * Support: http://www.com.app
 * License: http://www.com.app/license
 */
package com.app.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.app.entity.BaseEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author APP TEAM
 * @version 3.0
 */
public class EntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setCreateDate(new Date());
		entity.setModifyDate(new Date());
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setModifyDate(new Date());
	}

}