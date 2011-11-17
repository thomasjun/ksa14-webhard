package org.ksa14.webhard;

import org.ksa14.webhard.ui.AuthDialog;
import org.ksa14.webhard.ui.WebhardFrame;

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
		new AuthDialog();
		if (!AuthDialog.IsAuth())
			System.exit(0);
		
		WebhardFrame.Open();
		
		/*
		// Check authentication and open the main window if the authentication was successful.
		if (AuthDialog.Authenticate())
			WebhardFrame.Open();
		*/
	}
}
