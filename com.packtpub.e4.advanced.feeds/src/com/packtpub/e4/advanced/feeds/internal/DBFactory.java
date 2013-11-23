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
import java.sql.DriverManager;
import java.sql.SQLException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * This class is not used in the feeds implementation, but is used to
 * demonstrate the purpose of an IExecutableExtensionFactory. This might be
 * represented in a plugin.xml file as:
 * 
 * <pre>
 * &lt;database user="example" pass="pass" url="jdbc:h2:test" class="com.packtpub.e4.advanced.feeds.internal.DBFactory"/&gt;
 * </pre>
 */
public class DBFactory implements IExecutableExtension,
		IExecutableExtensionFactory {
	private String url;
	private String user;
	private String pass;
	@Override
	public Object create() throws CoreException {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			throw new CoreException(new Status(IStatus.ERROR,
					"com.packtpub.e4.advanced.feeds",
					"Failed to get driver connection for " + url, e));
		}
//new		ExtensionRegistry().addContribution(is, contributor, persist, contributionName, translationBundle, key)
	}
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		url = config.getAttribute("url");
		user = config.getAttribute("user");
		pass = config.getAttribute("pass");
	}
}
