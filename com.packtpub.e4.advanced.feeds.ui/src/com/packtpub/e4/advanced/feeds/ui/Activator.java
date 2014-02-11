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
import java.util.Hashtable;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedService;
import org.osgi.service.cm.ManagedServiceFactory;
/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements ManagedService {
	private boolean debug;
	public boolean isDebug() {
		return debug;
	}
	// The plug-in ID
	public static final String PLUGIN_ID = "com.packtpub.e4.advanced.feeds.ui"; //$NON-NLS-1$
	// The shared instance
	private static Activator plugin;
	/**
	 * The constructor
	 */
	public Activator() {
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Dictionary<String, String> properties = new Hashtable<String, String>();
		properties.put(Constants.SERVICE_PID,
				"com.packtpub.e4.advanced.feeds.ui");
		context.registerService(ManagedService.class, this, properties);
		properties = new Hashtable<String, String>();
		properties.put(Constants.SERVICE_PID, EmptyFeedParser.class.getName());
		context.registerService(ManagedService.class, new EmptyFeedParser(),
				properties);
		properties = new Hashtable<String, String>();
		properties.put(Constants.SERVICE_PID, EchoServiceFactory.class.getName());
		context.registerService(ManagedServiceFactory.class, new EchoServiceFactory(), properties);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	@Override
	public void updated(Dictionary<String, ?> configuration)
			throws ConfigurationException {
		debug = configuration != null
				&& "true".equals(configuration.get("debug"));
		if (debug) {
			System.out.println("Debugging enabled");
		} else {
			System.out.println("Debugging disabled");
		}
	}
}
