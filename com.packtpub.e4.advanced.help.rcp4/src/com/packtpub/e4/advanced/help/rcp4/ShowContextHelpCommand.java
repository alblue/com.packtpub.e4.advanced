/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.help.rcp4;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.help.HelpSystem;
import org.eclipse.help.IContext;
import org.eclipse.help.IHelpResource;
import org.eclipse.help.internal.base.BaseHelpSystem;
import org.eclipse.jface.dialogs.ErrorDialog;
@SuppressWarnings("restriction")
public class ShowContextHelpCommand {
	@Execute
	public void execute() {
		BaseHelpSystem.setMode(BaseHelpSystem.MODE_INFOCENTER);
		String helpContext = "org.eclipse.jdt.ui.members_view_context"; // obtain from UI
		IContext context = HelpSystem.getContext(helpContext);
		if (context == null) {
			String message = "Cannot find help for context " + context;
			ErrorDialog.openError(null, "Cannot find help", message,
					new Status(Status.ERROR, "", message));
		} else {
			IHelpResource[] topics = context.getRelatedTopics();
			if (topics.length == 0) {
				String message = "No help topics for context " + context;
				ErrorDialog.openError(null, "Cannot find help", message,
						new Status(Status.ERROR, "", message));
			} else {
				// Display first topic; add UI if multiple are returned
				BaseHelpSystem.getHelpDisplay().displayHelp(context,
						context.getRelatedTopics()[0], true);
			}
		}
	}
}