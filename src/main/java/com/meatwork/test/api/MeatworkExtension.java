package com.meatwork.test.api;

import com.meatwork.core.api.di.CDI;
import jakarta.inject.Inject;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;
import java.util.List;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class MeatworkExtension implements BeforeEachCallback {

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		List<Object> testInstances = context.getRequiredTestInstances().getAllInstances();
		Object o = testInstances.get(0);

		Field[] declaredFields = o
				.getClass()
				.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			if (!declaredField.isAnnotationPresent(Inject.class)) {
				continue;
			}

			declaredField.setAccessible(true);
			Class<?> type = declaredField.getType();
			declaredField.set(o, CDI.get(type));
		}
	}
}
