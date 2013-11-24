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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;
import org.eclipse.core.runtime.ContributorFactorySimple;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import com.packtpub.e4.advanced.feeds.FeedParserFactory;
public class Main {
	public static void main(String[] args) throws CoreException, IOException {
		RegistryFactory.setDefaultRegistryProvider(new IRegistryProvider() {
			private final IExtensionRegistry registry = RegistryFactory
					.createRegistry(getRegistryStrategy(), null, null);
			@Override
			public IExtensionRegistry getRegistry() {
				return registry;
			}
			private RegistryStrategy getRegistryStrategy() {
				File cache = new File(System.getProperty("java.io.tmpdir"),
						"cache");
				return new RegistryStrategy(new File[] { cache },
						new boolean[] { false });
			}
		});
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		boolean persist = true;
		Enumeration<URL> resources = Main.class.getClassLoader().getResources(
				"plugin.xml");
		while (resources.hasMoreElements()) {
			URL url = (URL) resources.nextElement();
			String plugin_xml = url.toString();
			String manifest_mf = plugin_xml.replace("plugin.xml",
					"META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(new URL(manifest_mf).openStream());
			String bsn = manifest.getMainAttributes().getValue(
					"Bundle-SymbolicName");
			int semi = bsn.indexOf(';');
			if (semi != -1) {
				bsn = bsn.substring(0, semi);
			}
			IContributor contributor = ContributorFactorySimple
					.createContributor(bsn);
			reg.addContribution(url.openStream(), contributor, persist,
					plugin_xml, null, null);
		}
		System.out.println(FeedParserFactory.getDefault().getFeedParsers());
		reg.stop(null);
	}
}
