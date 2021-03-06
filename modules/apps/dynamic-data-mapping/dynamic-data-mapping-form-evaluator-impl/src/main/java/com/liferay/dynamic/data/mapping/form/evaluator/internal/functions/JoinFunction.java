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

package com.liferay.dynamic.data.mapping.form.evaluator.internal.functions;

import com.liferay.dynamic.data.mapping.expression.DDMExpressionFunction;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.osgi.service.component.annotations.Component;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true, property = "ddm.form.evaluator.function.name=join",
	service = DDMExpressionFunction.class
)
public class JoinFunction implements DDMExpressionFunction {

	@Override
	public Object evaluate(Object... parameters) {
		if (parameters.length != 2) {
			throw new IllegalArgumentException("Two parameters are expected");
		}

		if (!(parameters[0] instanceof JSONArray)) {
			throw new IllegalArgumentException("JSONArray is expected");
		}

		JSONArray jsonArray = (JSONArray)parameters[0];

		StringBundler sb = new StringBundler(jsonArray.length() * 2 - 1);

		for (int i = 0; i < jsonArray.length(); i++) {
			sb.append(GetterUtil.getString(jsonArray.get(i)));

			if (i < (jsonArray.length() - 1)) {
				sb.append(CharPool.COMMA);
			}
		}

		return sb.toString();
	}

}