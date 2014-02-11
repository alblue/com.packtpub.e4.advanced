/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.feeds.ui;
import java.util.Dictionary;
import java.util.Map;
import java.util.TreeMap;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
public class EchoServiceFactory implements ManagedServiceFactory {
	private Map<String, EchoServer> echoServers = new TreeMap<String, EchoServer>();
	@Override
	public void deleted(String pid) {
		System.out.println("Removing echo server with pid " + pid);
		EchoServer removed = echoServers.remove(pid);
		if (removed != null) {
			removed.stop();
		}
	}
	@Override
	public String getName() {
		return "Echo service factory";
	}
	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
			throws ConfigurationException {
		if (properties != null) {
			String portString = properties.get("port").toString();
			try {
				int port = Integer.parseInt(portString);
				System.out.println("Creating new echo server on port " + port
						+ " with pid " + pid);
				echoServers.put(pid, new EchoServer(port));
			} catch (Exception e) {
				throw new ConfigurationException("port",
						"Cannot create a server on port " + portString, e);
			}
		} else if (echoServers.containsKey(pid)) {
			deleted(pid);
		}
	}
}
