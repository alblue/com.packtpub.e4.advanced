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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.osgi.service.log.LogService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.packtpub.e4.advanced.feeds.Feed;
import com.packtpub.e4.advanced.feeds.FeedItem;
import com.packtpub.e4.advanced.feeds.FeedItem.Builder;
import com.packtpub.e4.advanced.feeds.IFeedParser;
public class AtomFeedParser implements IFeedParser {
	private static final String ATOM = "http://www.w3.org/2005/Atom";
	private LogService log;
	private int max = Integer.MAX_VALUE;
	public void configure(Map<String, Object> properties) {
		max = Integer.MAX_VALUE;
		if (properties != null) {
			String maxStr = (String) properties.get("max");
			if (maxStr != null) {
				max = Integer.parseInt(maxStr);
			}
		}
		if (log != null) {
			log.log(LogService.LOG_INFO, "Configuring max with " + max);
		}
		System.out.println("Configuring max with " + max);
	}
	private String getTextValueOf(Node item, String element) {
		try {
			return ((Element) item).getElementsByTagNameNS(ATOM, element)
					.item(0).getTextContent();
		} catch (Exception e) {
			return null;
		}
	}
	private String getTextValueOfAttribute(Node item, String element,
			String attribute) {
		try {
			return ((Element) item).getElementsByTagNameNS(ATOM, element)
					.item(0).getAttributes().getNamedItem(attribute)
					.getNodeValue();
		} catch (Exception e) {
			return null;
		}
	}
	private Date parseDate(String date) {
		try {
			if (date.length() > 22 && date.charAt(22) == ':') {
				// Java doesn't handle : in the time zone format
				date = date.substring(0, 22) + date.substring(23);
			}
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(date);
		} catch (Exception e) {
			if (log != null) {
				log.log(LogService.LOG_WARNING, "Problem parsing date " + date);
			}
			return null;
		}
	}
	@Override
	public List<FeedItem> parseFeed(Feed feed) {
		try {
			List<FeedItem> feedItems = new ArrayList<FeedItem>();
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new URL(feed.getUrl()).openStream());
			NodeList items = doc.getElementsByTagNameNS(ATOM, "entry");
			for (int i = 0; i < items.getLength() && i < max; i++) {
				Node item = items.item(i);
				Builder feedItem = new FeedItem.Builder(feed);
				feedItem.setTitle(getTextValueOf(item, "title"));
				feedItem.setUrl(getTextValueOfAttribute(item, "link", "href"));
				feedItem.setDate(parseDate(getTextValueOf(item, "updated")));
				feedItems.add(feedItem.build());
			}
			if (log != null) {
				log.log(LogService.LOG_INFO, feedItems.size()
						+ " atom feed items parsed from " + feed.getUrl());
			}
			return feedItems;
		} catch (Exception e) {
			if (log != null) {
				log.log(LogService.LOG_WARNING, "Problem parsing feed " + e);
			}
			return null;
		}
	}
	public void setLog(LogService log) {
		this.log = log;
	}
	public void unsetLog(LogService log) {
		this.log = null;
	}
}
