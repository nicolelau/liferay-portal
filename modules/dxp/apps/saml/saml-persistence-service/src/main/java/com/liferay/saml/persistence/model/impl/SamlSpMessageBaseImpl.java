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

package com.liferay.saml.persistence.model.impl;

import com.liferay.saml.persistence.model.SamlSpMessage;
import com.liferay.saml.persistence.service.SamlSpMessageLocalServiceUtil;

/**
 * The extended model base implementation for the SamlSpMessage service. Represents a row in the &quot;SamlSpMessage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SamlSpMessageImpl}.
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpMessageImpl
 * @see SamlSpMessage
 * @generated
 */
public abstract class SamlSpMessageBaseImpl
	extends SamlSpMessageModelImpl implements SamlSpMessage {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a saml sp message model instance should use the <code>SamlSpMessage</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SamlSpMessageLocalServiceUtil.addSamlSpMessage(this);
		}
		else {
			SamlSpMessageLocalServiceUtil.updateSamlSpMessage(this);
		}
	}

}