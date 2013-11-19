/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.feeds.internal;
import java.util.Comparator;
import org.eclipse.core.runtime.IConfigurationElement;
public class FeedParserConfigurationComparator implements
		Comparator<IConfigurationElement> {
	private static final String PRIORITY = "priority";
	@Override
	public int compare(IConfigurationElement o1, IConfigurationElement o2) {
		String a1 = o1.getAttribute(PRIORITY);
		String a2 = o2.getAttribute(PRIORITY);
		return parseInt(a2) - parseInt(a1);
	}
	private int parseInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (Exception e) {
			return 0;
		}
	}
}
