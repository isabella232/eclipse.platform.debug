/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.debug.core.variables;

import org.eclipse.debug.core.DebugPlugin;

/**
 * Registry of all available refresh scope variables.
 */
public class RefreshScopeVariableRegistry extends ContextLaunchVariableRegistry {

	/**
	 * Creates the registry and loads the variables.
	 */
	public RefreshScopeVariableRegistry() {
		super(DebugPlugin.EXTENSION_POINT_REFRESH_LAUNCH_VARIABLES);
	}
	
}
