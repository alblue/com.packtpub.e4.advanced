/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.c;
public class Maths {
	static {
		System.loadLibrary("other");
		System.loadLibrary("maths");
	}
	private native static int nativeAdd(int a, int b);
	public static long add(long a, long b) {
		if (a < Integer.MAX_VALUE / 2 && a > Integer.MIN_VALUE / 2
				&& b < Integer.MAX_VALUE / 2 && b > Integer.MIN_VALUE / 2) {
			return nativeAdd((int) a, (int) b);
		} else {
			return a + b;
		}
	}
}
