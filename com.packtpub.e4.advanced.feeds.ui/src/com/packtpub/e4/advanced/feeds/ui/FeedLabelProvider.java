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
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import com.packtpub.e4.advanced.feeds.Feed;
import com.packtpub.e4.advanced.feeds.FeedItem;
public class FeedLabelProvider implements ILabelProvider {
	@Override
	public void addListener(ILabelProviderListener listener) {
	}
	@Override
	public void dispose() {
	}
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}
	@Override
	public void removeListener(ILabelProviderListener listener) {
	}
	@Override
	public Image getImage(Object element) {
		return PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_FILE);
	}
	@Override
	public String getText(Object element) {
		if (element instanceof Feed) {
			return ((Feed) element).getName();
		} else if (element instanceof FeedItem) {
			return ((FeedItem)element).getTitle();
		} else {
			return null;
		}
	}
}
