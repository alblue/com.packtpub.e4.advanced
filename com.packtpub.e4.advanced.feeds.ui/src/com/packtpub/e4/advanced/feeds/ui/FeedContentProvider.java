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
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
public class FeedContentProvider implements ITreeContentProvider,
		IResourceChangeListener {
	private static final Object[] NO_CHILDREN = new Object[0];
	private Viewer viewer;
	@Override
	public void dispose() {
		viewer = null;
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = viewer;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
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
						result[i++] = new Feed(entry.getValue(), entry.getKey());
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
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (viewer != null) {
			try {
				FeedsRefresher feedsChanged = new FeedsRefresher();
				event.getDelta().accept(feedsChanged);
			} catch (CoreException e) {
			}
		}
	}
	private class FeedsRefresher implements IResourceDeltaVisitor {
		@Override
		public boolean visit(IResourceDelta delta) throws CoreException {
			final IResource resource = delta.getResource();
			if (resource != null && "feeds".equals(resource.getFileExtension())) {
				((StructuredViewer) viewer).refresh(resource);
			}
			return true;
		}
	}
}
