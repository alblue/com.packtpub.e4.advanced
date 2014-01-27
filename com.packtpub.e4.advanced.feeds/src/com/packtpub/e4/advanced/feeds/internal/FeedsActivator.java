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
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.packtpub.e4.advanced.feeds.IFeedParser;
public class FeedsActivator implements BundleActivator {
	@Override
	public void start(BundleContext context) throws Exception {
		context.registerService(IFeedParser.class, new RSSFeedParser(), null);
		context.registerService(IFeedParser.class, new AtomFeedParser(), null);
		context.registerService(IFeedParser.class, new MockFeedParser(), null);
	}
	@Override
	public void stop(BundleContext context) throws Exception {
	}
}
