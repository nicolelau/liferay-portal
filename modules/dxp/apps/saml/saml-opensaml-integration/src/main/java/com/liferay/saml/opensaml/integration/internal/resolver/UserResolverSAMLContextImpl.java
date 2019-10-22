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

package com.liferay.saml.opensaml.integration.internal.resolver;

import com.liferay.saml.opensaml.integration.resolver.UserResolver;

import org.opensaml.messaging.context.MessageContext;
import org.opensaml.saml.saml2.core.Response;

/**
 * @author Carlos Sierra Andrés
 * @author Stian Sigvartsen
 */
public class UserResolverSAMLContextImpl
	extends SAMLContextImpl<Response, UserResolver>
	implements UserResolver.UserResolverSAMLContext {

	public UserResolverSAMLContextImpl(
		MessageContext<Response> messageContext) {

		super(messageContext);
	}

}