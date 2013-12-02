/*
 * Copyright (c) 2014, Alex Blewitt, Bandlem Ltd
 * Copyright (c) 2014, Packt Publishing Ltd
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.packtpub.e4.advanced.console;
import java.util.ArrayList;
import java.util.List;
import org.apache.felix.service.command.CommandSession;
import org.apache.felix.service.command.Function;
public class ListCommand {
	public List<Object> filter(CommandSession session, Function f,
			List<Object> list) throws Exception {
		List<Object> result = new ArrayList<Object>();
		for (Object object : list) {
			List<Object> args = new ArrayList<Object>(1);
			args.add(object);
			if (Boolean.TRUE.equals(f.execute(session, args))) {
				result.add(object);
			}
		}
		return result;
	}
}
