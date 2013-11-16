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
import java.util.List;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
public class FeedParserFactory {
	private static FeedParserFactory DEFAULT;
	public static FeedParserFactory getDefault() {
		if (DEFAULT == null) {
			DEFAULT = new FeedParserFactory();
		}
		return DEFAULT;
	}
	public List<IFeedParser> getFeedParsers() {
		ArrayList<IFeedParser> parsers = new ArrayList<IFeedParser>();
		IExtensionRegistry registry = RegistryFactory.getRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(
				"com.packtpub.e4.advanced.feeds", "feedParser");
		if (extensionPoint != null) {
			IConfigurationElement[] elements = extensionPoint
					.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				IConfigurationElement element = elements[i];
				try {
					Object parser = element.createExecutableExtension("class");
					if (parser instanceof IFeedParser) {
						parsers.add((IFeedParser) parser);
					}
				} catch (CoreException e) {
					// ignore or log as appropriate
				}
			}
		}
		return parsers;
	}
}
