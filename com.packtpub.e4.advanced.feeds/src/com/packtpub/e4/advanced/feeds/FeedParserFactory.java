/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.feeds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
public class FeedParserFactory {
	private static FeedParserFactory DEFAULT;
	private final ServiceTracker<IFeedParser, IFeedParser> st;
	private FeedParserFactory() {
		BundleContext context = FrameworkUtil.getBundle(FeedParserFactory.class).getBundleContext();
		Filter filter = null;
		try {
			filter = context
					.createFilter("(&(objectClass=com.packtpub.e4.advanced.feeds.IFeedParser))");
			// .createFilter("(&(objectClass=*.IFeedParser)(!(component.id=*)))");
		} catch (InvalidSyntaxException e) {
		}
		if (filter == null) {
			st = new ServiceTracker<IFeedParser, IFeedParser>(context,
					IFeedParser.class, null);
		} else {
			st = new ServiceTracker<IFeedParser, IFeedParser>(context, filter,
					null);
		}
		st.open();
	}
	public static FeedParserFactory getDefault() {
		if (DEFAULT == null) {
			DEFAULT = new FeedParserFactory();
		}
		return DEFAULT;
	}
	public List<IFeedParser> getFeedParsers() {
		ServiceReference<IFeedParser>[] refs = st.getServiceReferences();
		Arrays.sort(refs, new Comparator<ServiceReference<?>>() {
			@Override
			public int compare(ServiceReference<?> o1, ServiceReference<?> o2) {
				return o2.compareTo(o1);
			}
		});
		List<IFeedParser> list = new ArrayList<IFeedParser>(refs.length);
		for (ServiceReference<IFeedParser> ref : refs) {
			list.add(st.getService(ref));
		}
		return list;
	}
	@Override
	protected void finalize() throws Throwable {
		st.close();
		super.finalize();
	}
}
