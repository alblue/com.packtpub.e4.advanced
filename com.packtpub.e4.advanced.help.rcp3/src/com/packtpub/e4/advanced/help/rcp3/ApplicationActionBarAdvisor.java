/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.help.rcp3;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction helpContents;
	private IWorkbenchAction helpSearch;
	private IWorkbenchAction helpDynamic;
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	protected void makeActions(IWorkbenchWindow window) {
		helpContents = ActionFactory.HELP_CONTENTS.create(window);
		helpSearch = ActionFactory.HELP_SEARCH.create(window);
		helpDynamic = ActionFactory.DYNAMIC_HELP.create(window);
	}
	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager help = new MenuManager("Help", "help");
		help.add(helpContents);
		help.add(helpSearch);
		help.add(helpDynamic);
		menuBar.add(help);
	}
}
