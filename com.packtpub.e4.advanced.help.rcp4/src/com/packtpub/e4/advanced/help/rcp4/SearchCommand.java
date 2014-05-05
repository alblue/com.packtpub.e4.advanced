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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.help.internal.base.BaseHelpSystem;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
@SuppressWarnings("restriction")
public class SearchCommand {
	@Execute
	public void execute() throws UnsupportedEncodingException {
		BaseHelpSystem.setMode(BaseHelpSystem.MODE_INFOCENTER);
		InputDialog dialog = new InputDialog(null, "Search",
				"What do you want to search for?", null, null);
		if (Window.OK == dialog.open()) {
			String searchString = URLEncoder.encode(dialog.getValue(), "UTF-8");
			BaseHelpSystem.getHelpDisplay().displaySearch(
					"searchWord=" + searchString, "", true);
		}
	}
}
