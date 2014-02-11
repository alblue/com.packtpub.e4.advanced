/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.feeds.ui;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import com.packtpub.e4.advanced.feeds.Feed;
import com.packtpub.e4.advanced.feeds.FeedItem;
import com.packtpub.e4.advanced.feeds.IFeedParser;
public class EmptyFeedParser implements IFeedParser, ManagedService {
	private ServiceRegistration<IFeedParser> registration;
	@Override
	public void updated(Dictionary<String, ?> properties)
			throws ConfigurationException {
		BundleContext context = FrameworkUtil.getBundle(EmptyFeedParser.class)
				.getBundleContext();
		if (properties != null) {
			if (registration == null) {
				System.out
						.println("Registering EmptyFeedParser for the first time");
				registration = context.registerService(IFeedParser.class, this,
						properties);
			} else {
				System.out.println("Reconfiguring EmptyFeedParser");
				registration.setProperties(properties);
			}
		} else {
			if (registration != null) {
				System.out.println("Deconfiguring EmptyFeedParser");
				registration.unregister();
			}
			registration = null;
		}
	}
	@Override
	public List<FeedItem> parseFeed(Feed feed) {
		return new ArrayList<FeedItem>(0);
	}
}
