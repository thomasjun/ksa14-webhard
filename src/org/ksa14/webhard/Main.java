package org.ksa14.webhard;

import org.ksa14.webhard.ui.*;

/**
 * The main class
 * 
 * @author Jongwook
 */
public class Main {
	/**
	 * The entry point of the program.
	 * 
	 * @param args the command line arguments. will not be used.
	 */
	public static void main(String ... args) {
		// Check authentication
		if (!AuthDialog.Authenticate())
			return;
		
		// Open the main window if the authentication was successful.
		new WebhardFrame();
	}
}