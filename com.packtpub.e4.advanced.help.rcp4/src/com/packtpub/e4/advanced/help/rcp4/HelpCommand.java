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
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.help.internal.base.BaseHelpSystem;
@SuppressWarnings("restriction")
public class HelpCommand {
	@Execute
	public void execute() {
		BaseHelpSystem.setMode(BaseHelpSystem.MODE_INFOCENTER);
		BaseHelpSystem.getHelpDisplay().displayHelp(true);
	}
}
