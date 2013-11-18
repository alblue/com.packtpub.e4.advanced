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
import java.util.ArrayList;
import java.util.List;
import com.packtpub.e4.advanced.feeds.Feed;
import com.packtpub.e4.advanced.feeds.FeedItem;
import com.packtpub.e4.advanced.feeds.IFeedParser;
public class MockFeedParser implements IFeedParser {
	@Override
	public List<FeedItem> parseFeed(Feed feed) {
		List<FeedItem> items = new ArrayList<FeedItem>(3);
		items.add(new FeedItem.Builder(feed).setTitle("1st").setUrl("http://alblue.bandlem.com").build());
		items.add(new FeedItem.Builder(feed).setTitle("2nd").setUrl("http://www.packtpub.com").build());
		items.add(new FeedItem.Builder(feed).setTitle("3rd").setUrl("http://github.com/alblue/com.packtpub.e4.advanced").build());
		return items;
	}
}
