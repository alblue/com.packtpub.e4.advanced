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
public class MathsCommand {
	public Number add(Number n1, Number n2) {
		if (n1 instanceof Double || n1 instanceof Float || n2 instanceof Double
				|| n2 instanceof Float) {
			return new Double(n1.doubleValue() + n2.doubleValue());
		} else {
			return new Long(n1.longValue() + n2.longValue());
		}
	}
	public Number subtract(Number n1, Number n2) {
		if (n1 instanceof Double || n1 instanceof Float || n2 instanceof Double
				|| n2 instanceof Float) {
			return new Double(n1.doubleValue() - n2.doubleValue());
		} else {
			return new Long(n1.longValue() - n2.longValue());
		}
	}
	public Number multiply(Number n1, Number n2) {
		if (n1 instanceof Double || n1 instanceof Float || n2 instanceof Double
				|| n2 instanceof Float) {
			return new Double(n1.doubleValue() * n2.doubleValue());
		} else {
			return new Long(n1.longValue() * n2.longValue());
		}
	}
	public Number divide(Number n1, Number n2) {
		if (n1 instanceof Double || n1 instanceof Float || n2 instanceof Double
				|| n2 instanceof Float) {
			return new Double(n1.doubleValue() / n2.doubleValue());
		} else {
			return new Long(n1.longValue() / n2.longValue());
		}
	}
}
