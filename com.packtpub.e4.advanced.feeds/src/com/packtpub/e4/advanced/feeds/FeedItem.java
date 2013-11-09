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
import java.util.Date;
public class FeedItem {
	public static class Builder {
		private FeedItem item;
		public Builder(Feed feed) {
			item = new FeedItem(feed);
		}
		public FeedItem build() {
			if(item.date == null) {
				item.date = new Date();
			}
			return item;
		}
		public Builder setDate(Date date) {
			item.date = date;
			return this;
		}
		public Builder setFeed(Feed feed) {
			item.feed = feed;
			return this;
		}
		public Builder setHtml(String html) {
			item.html = html;
			return this;
		}
		public Builder setUrl(String url) {
			item.url = url;
			return this;
		}
		public Builder setTitle(String title) {
			item.title = title;
			return this;
		}
	}
	private Date date;
	private Feed feed;
	private String html;
	private String url;
	private String title;
	private FeedItem(Feed feed) {
		this.feed = feed;
	}
	public Date getDate() {
		return date;
	}
	public Feed getFeed() {
		return feed;
	}
	public String getHtml() {
		return html;
	}
	public String getUrl() {
		return url;
	}
	public String getTitle() {
		return title;
	}
}
