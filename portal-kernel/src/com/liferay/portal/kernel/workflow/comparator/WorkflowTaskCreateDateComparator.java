/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.workflow.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowTask;

import java.util.Date;

/**
 * <a href="WorkflowTaskCreateDateComparator.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowTaskCreateDateComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "createDate ASC, workflowTaskId ASC";

	public static String ORDER_BY_DESC = "createDate DESC, workflowTaskId DESC";

	public static String[] ORDER_BY_FIELDS = {"createDate", "workflowTaskId"};

	public WorkflowTaskCreateDateComparator() {
		this(false);
	}

	public WorkflowTaskCreateDateComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object obj1, Object obj2) {
		WorkflowTask workflowTask1 = (WorkflowTask)obj1;
		WorkflowTask workflowTask2 = (WorkflowTask)obj2;

		Date createDate1 = workflowTask1.getCreateDate();
		Date createDate2 = workflowTask2.getCreateDate();

		int value = createDate1.compareTo(createDate2);

		if (value == 0) {
			Long workflowTaskId1 = workflowTask1.getWorkflowTaskId();
			Long workflowTaskId2 = workflowTask2.getWorkflowTaskId();

			value = workflowTaskId1.compareTo(workflowTaskId2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	public boolean isAscending() {
		return _asc;
	}

	private boolean _asc;

}