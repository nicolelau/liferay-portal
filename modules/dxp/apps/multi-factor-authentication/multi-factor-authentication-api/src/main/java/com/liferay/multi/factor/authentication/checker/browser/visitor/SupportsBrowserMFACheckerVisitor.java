/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.multi.factor.authentication.checker.browser.visitor;

import com.liferay.multi.factor.authentication.checker.browser.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.checker.visitor.BaseMFACheckerVisitor;

/**
 * @author Carlos Sierra Andrés
 */
public class SupportsBrowserMFACheckerVisitor extends BaseMFACheckerVisitor {

	public SupportsBrowserMFACheckerVisitor() {
		super(BrowserMFAChecker.class::isInstance);
	}

}