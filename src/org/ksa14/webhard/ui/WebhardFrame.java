package org.ksa14.webhard.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.ksa14.webhard.sftp.SftpAdapter;

/**
 * WebhardFrame is a JFrame subclass that represents the main window 
 * 
 * @author Jongwook
 */
public class WebhardFrame extends JFrame{
	public static final long serialVersionUID=0L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static WebhardFrame TheInstance;
	/**
	 * Initialize the main webhard window. 
	 * GUI components are initialized by WebhardPanel class.
	 */
	private WebhardFrame() {
		// Create the main window with the title
		super("KSA14 Webhard Client");
		
		// Try to set system native look-and-feel
		SwingUtility.setSystemLookAndFeel();
		
		// Load Icon
		URL urlIcon = getClass().getResource("/res/icon48.png");
		Image imgIcon = Toolkit.getDefaultToolkit().getImage(urlIcon);
		setIconImage(imgIcon);
		
		// Make the process terminate when the window is closed 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Set the default window size and location
		int sW = (int)getToolkit().getScreenSize().getWidth();
		int sH = (int)getToolkit().getScreenSize().getHeight();
		int wW = Math.min(sW, WIDTH);
		int wH = Math.min(sH, HEIGHT);
		setSize(wW, wH);
		setLocation((sW - wW) / 2, (sH - wH) / 2);
		
		// Set the background color
		getContentPane().setBackground(Color.lightGray);
		
		// Add the main panel
		add(new WebhardPanel());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Exit();
			}
		});
		
		// Finally, show the window up
		setVisible(true);
	}
	
	public static WebhardFrame GetInstance() {
		return (TheInstance == null) ? TheInstance = new WebhardFrame() : TheInstance;
	}
	
	public static void Open() {
		GetInstance();
	}
	
	public static void Exit() {
		if (JOptionPane.showOptionDialog(null, "KSA14 Webhard 를 종료합니다", "KSA14 Webhard Client", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
			SftpAdapter.Disconnect();
			System.exit(0);
		}
	}

}
