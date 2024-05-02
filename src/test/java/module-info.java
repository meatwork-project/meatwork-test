/*
 * Copyright (c) 2016 Taliro.
 * All rights reserved.
 */
module com.meatwork.test.test {
	requires com.meatwork.test;
	requires com.meatwork.core;
	requires org.junit.jupiter.api;
	requires jakarta.inject;

	exports com.meatwork.test.test.services;

	opens com.meatwork.test.test;
}