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
import java.util.Arrays;
import java.util.List;
import org.osgi.util.tracker.ServiceTracker;
import com.packtpub.e4.advanced.feeds.internal.FeedsActivator;
public class FeedParserFactory {
	private static FeedParserFactory DEFAULT;
	private final ServiceTracker<IFeedParser, IFeedParser> st;
	private FeedParserFactory() {
		st = new ServiceTracker<IFeedParser, IFeedParser>(
				FeedsActivator.getContext(), IFeedParser.class, null);
		st.open();
	}
	public static FeedParserFactory getDefault() {
		if (DEFAULT == null) {
			DEFAULT = new FeedParserFactory();
		}
		return DEFAULT;
	}
	public List<IFeedParser> getFeedParsers() {
		return Arrays.asList(st.getServices(new IFeedParser[] {}));
	}
	@Override
	protected void finalize() throws Throwable {
		st.close();
		super.finalize();
	}
}
