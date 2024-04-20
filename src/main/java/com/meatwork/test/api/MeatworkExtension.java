package com.meatwork.test.api;

import com.google.inject.Inject;
import com.meatwork.tools.api.di.CDI;
import com.meatwork.tools.api.service.Application;
import org.junit.jupiter.api.extension.BeforeAllCallback;
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
		Class<?> aClass = o.getClass();

		String[] arguments = null;
		if (aClass.isAnnotationPresent(ApplicationArguments.class)) {
			ApplicationArguments annotationsByType = aClass.getAnnotation(ApplicationArguments.class);
			arguments = annotationsByType.value();
		}

		Application.run(arguments);


		Field[] declaredFields = o
				.getClass()
				.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			if (!declaredField.isAnnotationPresent(Inject.class) && !declaredField.isAnnotationPresent(jakarta.inject.Inject.class)) {
				continue;
			}

			declaredField.setAccessible(true);
			Class<?> type = declaredField.getType();
			declaredField.set(o, CDI.get(type));
		}
	}
}
