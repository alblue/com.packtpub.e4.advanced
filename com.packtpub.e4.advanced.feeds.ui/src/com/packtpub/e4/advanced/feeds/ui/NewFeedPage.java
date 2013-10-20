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
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
public class NewFeedPage extends WizardPage {
	private Text descriptionText;
	private Text urlText;
	protected NewFeedPage() {
		super("Add New Feed");
	}
	@Override
	public void createControl(Composite parent) {
		Composite page = new Composite(parent, SWT.NONE);
		setControl(page);
		setPageComplete(false);
	}
	public String getDescription() {
		return getTextFrom(descriptionText);
	}
	private String getTextFrom(Text text) {
		return text == null || text.isDisposed() ? null : text.getText();
	}
	public String getURL() {
		return getTextFrom(urlText);
	}
}
