/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.jenkins.results.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *	@author Yi-Chen Tsai
 */

@RunWith(Parameterized.class)
public class CustomScriptTest {

	@Parameters(name = "{0}")
	public static Collection<Object[]> getList() throws Exception {
		Collection<Object[]> testNameList = new ArrayList<>();

		testNameList.add(new String[] {System.getProperty("testName")});

		return testNameList;
	}

	public CustomScriptTest(String testName) throws Exception {
	}

	@Test
	public void test() throws Exception {
		String testResult = System.getProperty("actual");

		String expectedResult = System.getProperty("expected");

		Assert.assertEquals(expectedResult, testResult);
	}

}