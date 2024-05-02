package com.meatwork.test.test.services;

import com.meatwork.core.api.di.Service;
import jakarta.inject.Inject;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@Service
public class AnService2Impl implements AnService2{

	private final AnService anService;

	@Inject
	public AnService2Impl(AnService anService) {
		this.anService = anService;
	}

	@Override
	public boolean isRun() {
		return anService.isRun();
	}
}
