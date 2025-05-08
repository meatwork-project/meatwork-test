package com.meatwork.test.api;

import com.meatwork.core.api.di.CDI;
import com.meatwork.core.api.service.MeatworkApplication;
import jakarta.inject.Inject;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class MeatworkExtension implements BeforeEachCallback {

	@MeatworkApplication
	private static class ApplicationTest {}

	@Override
	public void beforeEach(ExtensionContext context) throws Exception {
		CDI.init(ApplicationTest.class);
		List<Object> testInstances = context.getRequiredTestInstances().getAllInstances();
		Object o = testInstances.getFirst();

		Field[] declaredFields = o
				.getClass()
				.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			if (!declaredField.isAnnotationPresent(Inject.class)) {
				continue;
			}

			declaredField.setAccessible(true);
			Class<?> type = declaredField.getType();
			if(Set.class.equals(type)) {
				var actualType = ((ParameterizedType) declaredField.getGenericType()).getActualTypeArguments()[0];
				type = (Class<?>) actualType;
			}
			declaredField.set(o, CDI.get(type));
		}
	}
}
