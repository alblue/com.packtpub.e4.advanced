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
import org.osgi.service.log.LogService;
import com.packtpub.e4.advanced.feeds.Feed;
import com.packtpub.e4.advanced.feeds.FeedItem;
import com.packtpub.e4.advanced.feeds.IFeedParser;
public class MockFeedParser implements IFeedParser {
	private int numberOfItems = 3;
	private LogService log;
	@Override
	public List<FeedItem> parseFeed(Feed feed) {
		List<FeedItem> items = new ArrayList<FeedItem>(numberOfItems);
		for (int i = 0; i < numberOfItems; i++) {
			items.add(new FeedItem.Builder(feed).setTitle("Item " + i)
					.setUrl("http://example.com/item/" + i).build());
		}
		return items;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
		if (log != null) {
			log.log(LogService.LOG_INFO, "Setting number of items to "
					+ numberOfItems);
		}
	}
	public void setLog(LogService log) {
		this.log = log;
	}
}
