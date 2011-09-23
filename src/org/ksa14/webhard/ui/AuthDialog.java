package org.ksa14.webhard.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.ksa14.webhard.sftp.*;

public class AuthDialog extends JDialog {
	private static final long serialVersionUID = 0L;
	public static final int wWidth = 460;
	public static final int wHeight = 200;
	
	private JTextField TextID;
	private JPasswordField TextPW;
	private JButton BtnConnect;
	
	private BufferedImage ImgLogo;
	
	private static boolean Authed = false;
	private SftpUtil Sftp;
	
	public static boolean Authenticate(SftpUtil su) {
		new AuthDialog(su);
		return Authed;
	}

	public AuthDialog(SftpUtil su) {
		// Set absolute layout
		this.setLayout(null);
		
		try {
			// Load KSA logo image
			ImgLogo = ImageIO.read(new File("ksa.jpg"));
		} catch (IOException e) {
			// Do nothing
		}
		
		// Add components to input login information
		JLabel LabelID = new JLabel("학번");
		LabelID.setBounds(195, 30, 60, 15);
		this.add(LabelID);
		
		JLabel LabelPW = new JLabel("비밀번호");
		LabelPW.setBounds(195, 70, 60, 15);
		this.add(LabelPW);
		
		TextID = new JTextField();
		TextID.setBounds(260, 27, 155, 21);
		TextID.setColumns(20);
		this.add(TextID);
		
		TextPW = new JPasswordField();
		TextPW.setBounds(260, 67, 155, 21);
		TextPW.setColumns(20);
		TextPW.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					Authed = RequestAuth(TextID.getText(), new String(TextPW.getPassword()));
					if (Authed)
						dispose();
				}
			}
		});
		this.add(TextPW);
		
		// Add buttons to connect or exit
		BtnConnect = new JButton("접속");
		BtnConnect.setBounds(270, 110, 60, 25);
		BtnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authed = RequestAuth(TextID.getText(), new String(TextPW.getPassword()));
				if (Authed)
					dispose();
			}
		});
		this.add(BtnConnect);
		
		JButton BtnExit = new JButton("종료");
		BtnExit.setBounds(350, 110, 60, 25);
		BtnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authed = false;
				dispose();
			}
		});
		this.add(BtnExit);
		
		// Set window properties
		this.setTitle("KSA14 Webhard Login");
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Set window size and location
		int sW = (int)getToolkit().getScreenSize().getWidth();
		int sH = (int)getToolkit().getScreenSize().getHeight();
		this.setSize(wWidth, wHeight);
		this.setLocation((sW - wWidth) / 2, (sH - wHeight) / 2);
		this.setResizable(false);
		
		// Sftp utility
		this.Sftp = su;
		
		// Show window
		this.setVisible(true);
	}
	
	// Override paint method
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(ImgLogo, 25, 50, null);
	}
	
	public boolean RequestAuth(String id, String pw) {
		// TODO : Code for authenticating
		if ((id.trim().length() == 0) || (pw.trim().length() == 0)) {
			JOptionPane.showMessageDialog(null, "학번과 비밀번호를 입력해주세요", "KSA14 Webhard Login", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (!Sftp.Connect(id, pw)) {
			JOptionPane.showMessageDialog(null, "접속에 실패했습니다", "KSA14 Webhard Login", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
}
