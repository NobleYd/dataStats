/*
 * Copyright 2005-2013 com.app. All rights reserved.
 * Support: http://www.com.app
 * License: http://www.com.app/license
 */
package com.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 资源不存在异常
 * 
 * @author APP TEAM
 * @version 3.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -9208522773597070069L;

}