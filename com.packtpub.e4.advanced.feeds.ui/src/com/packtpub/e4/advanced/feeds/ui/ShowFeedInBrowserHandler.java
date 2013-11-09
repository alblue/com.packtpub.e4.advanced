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
import java.net.URL;
import java.util.Iterator;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;
import com.packtpub.e4.advanced.feeds.Feed;
public class ShowFeedInBrowserHandler extends AbstractHandler {
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			Iterator<?> it = ((IStructuredSelection) selection).iterator();
			while (it.hasNext()) {
				Object object = it.next();
				if (object instanceof Feed) {
					String url = ((Feed) object).getUrl();
					try {
						PlatformUI.getWorkbench().getBrowserSupport()
								.createBrowser(url).openURL(new URL(url));
					} catch (Exception e) {
						StatusManager
								.getManager()
								.handle(new Status(Status.ERROR,
										Activator.PLUGIN_ID,
										"Could not open browser for " + url, e),StatusManager.LOG | StatusManager.SHOW);
					}
				}
			}
		}
		return null;
	}
}
