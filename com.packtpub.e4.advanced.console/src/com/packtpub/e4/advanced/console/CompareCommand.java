/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.console;
public class CompareCommand {
	public boolean eq(Object a, Object b) {
		if (a instanceof Number && b instanceof Number) {
			if (a instanceof Double || a instanceof Float
					|| b instanceof Double || b instanceof Float) {
				return ((Number) a).doubleValue() == ((Number) b).doubleValue();
			} else {
				return ((Number) a).longValue() == ((Number) b).longValue();
			}
		} else {
			return a.equals(b);
		}
	}
	public boolean gt(Comparable<Object> a, Comparable<Object> b) {
		return a.compareTo(b) > 0;
	}
	public boolean lt(Comparable<Object> a, Comparable<Object> b) {
		return a.compareTo(b) < 0;
	}
	public int compare(Comparable<Object> a, Comparable<Object> b) {
		return a.compareTo(b);
	}
}
