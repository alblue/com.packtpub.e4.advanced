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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
public class NewFeedWizard extends Wizard implements INewWizard {
	public static final String FEEDS_FILE = "news.feeds";
	public static final String FEEDS_PROJECT = "bookmarks";
	/**
	 * This method permits the wizard to be tested outside of an Eclipse
	 * runtime.
	 * 
	 * It is not intended to demonstrate good practice.
	 */
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		new WizardDialog(shell, new NewFeedWizard()).open();
		display.dispose();
	}
	private NewFeedPage newFeedPage = new NewFeedPage();
	private NewFeedPreviewPage newFeedPreviewPage = new NewFeedPreviewPage();
	private synchronized void addFeed(String url, String description,
			IProgressMonitor monitor) throws CoreException, IOException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		if(subMonitor.isCanceled())
			return;
		Properties feeds = new Properties();
		IFile file = getFile(FEEDS_PROJECT, FEEDS_FILE, subMonitor);
		subMonitor.worked(1);
		if (file.exists()) {
			feeds.load(file.getContents());
		}
		if(subMonitor.isCanceled())
			return;
		feeds.setProperty(url, description);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		feeds.store(baos, null);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		if(subMonitor.isCanceled())
			return;
		if (file.exists()) {
			file.setContents(bais, true, false, subMonitor);
		} else {
			file.create(bais, true, subMonitor);
		}
		subMonitor.worked(1);
		if (monitor != null) {
			monitor.done();
		}
	}
	@Override
	public void addPages() {
		addPage(newFeedPage);
		addPage(newFeedPreviewPage);
		setHelpAvailable(true);
		setNeedsProgressMonitor(true);
	}
	private IFile getFile(String project, String name, IProgressMonitor monitor)
			throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject bookmarks = workspace.getRoot().getProject(project);
		if (!bookmarks.exists()) {
			bookmarks.create(monitor);
		}
		if (!bookmarks.isOpen()) {
			bookmarks.open(monitor);
		}
		return bookmarks.getFile(name);
	}
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
	@Override
	public boolean performFinish() {
		final String url = newFeedPage.getURL();
		final String description = newFeedPage.getDescription();
		try {
			boolean fork = false;
			boolean cancelable = true;
			getContainer().run(fork, cancelable, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					try {
						if (url != null && description != null) {
							addFeed(url, description, monitor);
						}
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
				}
			});
			return cancelable;
		} catch (InvocationTargetException e) {
			newFeedPage.setMessage(e.getTargetException().toString(),
					IMessageProvider.ERROR);
			return false;
		} catch (InterruptedException e) {
			return true;
		}
	}
}
