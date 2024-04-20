package com.meatwork.test.api;

import com.google.inject.Inject;
import com.meatwork.tools.api.di.CDI;
import com.meatwork.tools.api.service.Application;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
public class MeatworkExtension implements ParameterResolver {

	public MeatworkExtension() {
		Application.run();
	}


	@Override
	public boolean supportsParameter(ParameterContext parameterContext,
	                                 ExtensionContext extensionContext) throws ParameterResolutionException {
		return parameterContext.getParameter().isAnnotationPresent(Inject.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext,
	                               ExtensionContext extensionContext) throws ParameterResolutionException {
		return CDI.get(parameterContext.getParameter().getType());
	}
}
