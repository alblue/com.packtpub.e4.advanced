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
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
public class FeedContentProvider implements ITreeContentProvider {
	private static final Object[] NO_CHILDREN = new Object[0];
	@Override
	public void dispose() {
	}
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = NO_CHILDREN;
		if (parentElement instanceof IResource) {
			IResource resource = (IResource) parentElement;
			if (resource.getName().endsWith(".feeds")) {
				try {
					Properties properties = new Properties();
					InputStream stream = resource.getLocationURI().toURL().openStream();
					properties.load(stream);
					stream.close();
					result = new Object[properties.size()];
					int i = 0;
					@SuppressWarnings("rawtypes")
					Iterator it = properties.entrySet().iterator();
					while (it.hasNext()) {
						@SuppressWarnings("unchecked")
						Map.Entry<String, String> entry = (Entry<String, String>) it
								.next();
						result[i++] = new Feed(
								entry.getValue(),entry.getKey());
					}
				} catch (Exception e) {
					return NO_CHILDREN;
				}
			}
		}
		return result;
	}
	@Override
	public Object getParent(Object element) {
		return null;
	}
	@Override
	public boolean hasChildren(Object element) {
		return false;
	}
}
