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
import java.util.Arrays;
import java.util.List;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
public class BundlesCommand {
	private BundleContext context;
	public void activate(BundleContext context) {
		this.context = context;
	}
	public void print() {
		Bundle[] bundles = context.getBundles();
		for (int i = 0; i < bundles.length; i++) {
			Bundle bundle = bundles[i];
			System.out.println(bundle.getBundleId() + " "
					+ bundle.getSymbolicName());
		}
	}
	public List<Bundle> list() {
		return Arrays.asList(context.getBundles());
	}
}
