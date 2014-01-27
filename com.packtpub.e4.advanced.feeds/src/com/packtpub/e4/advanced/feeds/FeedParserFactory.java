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
import java.util.Collection;
import java.util.List;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import com.packtpub.e4.advanced.feeds.internal.FeedsActivator;
public class FeedParserFactory {
	private static FeedParserFactory DEFAULT;
	public static FeedParserFactory getDefault() {
		if (DEFAULT == null) {
			DEFAULT = new FeedParserFactory();
		}
		return DEFAULT;
	}
	public List<IFeedParser> getFeedParsers() {
		List<IFeedParser> parsers = new ArrayList<IFeedParser>();
		BundleContext context = FeedsActivator.getContext();
		try {
			Collection<ServiceReference<IFeedParser>> references = 
				context.getServiceReferences(IFeedParser.class, null);
			for (ServiceReference<IFeedParser> reference : references) {
				parsers.add(context.getService(reference));
				context.ungetService(reference);
			}
		} catch (InvalidSyntaxException e) {
			// ignore
		}
		return parsers;
	}
}
