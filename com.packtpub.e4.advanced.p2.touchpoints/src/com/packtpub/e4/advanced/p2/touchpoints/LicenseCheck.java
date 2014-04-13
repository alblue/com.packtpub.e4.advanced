/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.p2.touchpoints;
import java.io.File;
import java.util.Map;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.engine.spi.ProvisioningAction;
/**
 * Can be used by adding to a p2.inf file as follows:
 * 
 * <pre>
 * instructions.install=licenseCheck(licenseFile:/path/to/file)
 * </pre>
 */
public class LicenseCheck extends ProvisioningAction {
	@Override
	public IStatus execute(Map<String, Object> parameters) {
		if (isLicensed((String) parameters.get("licenseFile"))) {
			return Status.OK_STATUS;
		}
		return new Status(Status.ERROR, "com.packtpub.e4.advanced.p2.touchpoints", "The plug-in is not licensed");
	}
	private boolean isLicensed(String file) {
		return file != null && new File(file).exists();
	}
	@Override
	public IStatus undo(Map<String, Object> parameters) {
		// NOP
		return Status.OK_STATUS;
	}
}
