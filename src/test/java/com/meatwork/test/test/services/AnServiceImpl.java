package com.meatwork.test.test.services;

import com.meatwork.core.api.di.Service;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@Service
public class AnServiceImpl implements AnService {
	@Override
	public boolean isRun() {
		return true;
	}
}
