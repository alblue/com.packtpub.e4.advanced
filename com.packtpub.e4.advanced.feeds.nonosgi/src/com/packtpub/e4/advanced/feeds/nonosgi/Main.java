/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.feeds.nonosgi;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import org.eclipse.core.runtime.ContributorFactorySimple;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import com.packtpub.e4.advanced.feeds.FeedParserFactory;
public class Main {
	public static void main(String[] args) throws CoreException, IOException {
		RegistryFactory.setDefaultRegistryProvider(new IRegistryProvider() {
			private final IExtensionRegistry registry = RegistryFactory
					.createRegistry(null, null, null);
			@Override
			public IExtensionRegistry getRegistry() {
				return registry;
			}
		});
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		IContributor contributor = ContributorFactorySimple
				.createContributor("com.packtpub.e4.advanced.feeds");
		String plugin_xml = "../com.packtpub.e4.advanced.feeds/plugin.xml";
		reg.addContribution(new FileInputStream(plugin_xml), contributor,
				false, plugin_xml, null, null);
		IExtensionPoint extensionPoint = reg.getExtensionPoint(
				"com.packtpub.e4.advanced.feeds", "feedParser");
		System.out.println(Arrays.asList(extensionPoint.getExtensions()));
		System.out.println(FeedParserFactory.getDefault().getFeedParsers());
		reg.stop(null);
	}
}
