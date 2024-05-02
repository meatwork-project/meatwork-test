package com.meatwork.test.test;

import com.meatwork.test.api.MeatworkExtension;
import com.meatwork.test.test.services.AnService2;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
@ExtendWith(MeatworkExtension.class)
public class InjectTestInTest {

	@Inject
	private AnService2 anService;

	@Test
	public void testInject() {
		Assertions.assertNotNull(anService);
		Assertions.assertTrue(anService.isRun());
	}

}
