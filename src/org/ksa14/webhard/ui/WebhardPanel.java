package org.ksa14.webhard.ui;

import java.awt.*;
import javax.swing.*;

/**
 * WebhardPanel maintains all components in the client area of the main webhard window
 * 
 * @author Jongwook
 */
public class WebhardPanel extends JPanel {
	public static final long serialVersionUID = 0L;

	protected JToolBar toolBar;
	protected DirectoryTree tree;
	protected FileList files;
	
	/**
	 * Initializes the GUI components of the main webhard window
	 */
	public WebhardPanel() {
		this.setBackground(Color.lightGray);
		// Set layout
		this.setLayout(new BorderLayout());
				
		// Initialize the tool bar
		this.toolBar = new JToolBar("KSA14 Webhard Tool Bar");
		this.add(this.toolBar, BorderLayout.PAGE_START);
		
		// Initialize the directory tree
		this.tree = new DirectoryTree();
		this.add(this.tree, BorderLayout.LINE_START);
		
		// Initialize the file list 
		this.files = new FileList();
		this.add(new JScrollPane(this.files), BorderLayout.CENTER);
	}
}