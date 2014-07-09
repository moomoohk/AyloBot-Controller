package com.moomoohk.AyloBotController;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.moomoohk.Mootilities.OSUtils.OSUtils;
import com.moomoohk.Mootilities.OSUtils.OSUtils.OS;

/**
 * 
 * @author Meshulam Silk (moomoohk@ymail.com)
 * @since Jun 26, 2014
 */
public class LoginFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JTextField txtUsername;
	private final JPasswordField pwdOAuthToken;
	private final JButton btnSubmit;

	public static enum LoginState
	{
		CONNECTING, LOGIN_SUCCESS, LOGIN_FAIL, LOGIN_TIMEOUT;
	}

	public LoginFrame()
	{
		setTitle("AyloBot Controller by moomoohk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(450, 250));
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		this.contentPane.setLayout(sl_contentPane);

		JLabel lblLoginToTwitch = new JLabel("LOGIN TO TWITCH");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLoginToTwitch, -10, SpringLayout.EAST, this.contentPane);
		lblLoginToTwitch.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 30));
		lblLoginToTwitch.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLoginToTwitch, 10, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLoginToTwitch, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLoginToTwitch, 61, SpringLayout.NORTH, this.contentPane);
		this.contentPane.add(lblLoginToTwitch);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		sl_contentPane.putConstraint(SpringLayout.NORTH, separator, 6, SpringLayout.SOUTH, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, separator, 18, SpringLayout.SOUTH, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, lblLoginToTwitch);
		this.contentPane.add(separator);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUsername, 101, SpringLayout.WEST, this.contentPane);
		this.contentPane.add(lblUsername);

		this.txtUsername = new JTextField();
		this.txtUsername.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, this.txtUsername, 6, SpringLayout.EAST, lblUsername);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.txtUsername, -10, SpringLayout.EAST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUsername, 6, SpringLayout.NORTH, this.txtUsername);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.txtUsername, 6, SpringLayout.SOUTH, separator);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.txtUsername, 34, SpringLayout.SOUTH, separator);
		this.contentPane.add(this.txtUsername);
		this.txtUsername.setColumns(10);
		this.txtUsername.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke)
			{
				if (ke.getKeyCode() == 10)
					LoginFrame.this.pwdOAuthToken.requestFocus();
				if (ke.getKeyCode() == 27)
					LoginFrame.this.txtUsername.setText("");
			}

			@Override
			public void keyReleased(KeyEvent ke)
			{
				if (LoginFrame.this.pwdOAuthToken.getPassword().length > 0 && LoginFrame.this.txtUsername.getText().length() > 0)
					LoginFrame.this.btnSubmit.setEnabled(true);
				else
					LoginFrame.this.btnSubmit.setEnabled(false);
			}
		});

		JLabel lblPassword = new JLabel("OAuth Token:");
		lblPassword.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPassword, 0, SpringLayout.EAST, lblUsername);
		this.contentPane.add(lblPassword);

		this.pwdOAuthToken = new JPasswordField();
		this.pwdOAuthToken.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, this.pwdOAuthToken, 6, SpringLayout.EAST, lblPassword);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.pwdOAuthToken, -10, SpringLayout.EAST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPassword, 6, SpringLayout.NORTH, this.pwdOAuthToken);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.pwdOAuthToken, 6, SpringLayout.SOUTH, this.txtUsername);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.pwdOAuthToken, 34, SpringLayout.SOUTH, this.txtUsername);
		this.contentPane.add(this.pwdOAuthToken);
		this.pwdOAuthToken.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent ke)
			{
				if (ke.getKeyCode() == 10)
					LoginFrame.this.btnSubmit.doClick();
				if (ke.getKeyCode() == 27)
					LoginFrame.this.pwdOAuthToken.setText("");
			}

			@Override
			public void keyReleased(KeyEvent ke)
			{
				if (LoginFrame.this.pwdOAuthToken.getPassword().length > 0 && LoginFrame.this.txtUsername.getText().length() > 0)
					LoginFrame.this.btnSubmit.setEnabled(true);
				else
					LoginFrame.this.btnSubmit.setEnabled(false);
			}
		});

		this.btnSubmit = new JButton("Submit");
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnSubmit, -10, SpringLayout.EAST, this.contentPane);
		this.btnSubmit.setEnabled(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnSubmit, 6, SpringLayout.SOUTH, this.pwdOAuthToken);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnSubmit, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnSubmit, 34, SpringLayout.SOUTH, this.pwdOAuthToken);
		this.contentPane.add(this.btnSubmit);

		final JLabel lblHelp = new JLabel("<html>Where do I find my OAuth Token?<code>      </code></html>");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHelp, 6, SpringLayout.SOUTH, this.btnSubmit);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHelp, 0, SpringLayout.WEST, lblLoginToTwitch);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblHelp, -3, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblHelp, -10, SpringLayout.EAST, this.contentPane);
		lblHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHelp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHelp.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.ITALIC, 13));
		this.contentPane.add(lblHelp);
		setLocationRelativeTo(null);
		lblHelp.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent me)
			{
				lblHelp.setText("<html><u>Where do I find my OAuth Token?</u><code>      </code></html>");
			}

			@Override
			public void mouseExited(MouseEvent me)
			{
				lblHelp.setText("<html>Where do I find my OAuth Token?<code>      </code></html>");
			}

			@Override
			public void mousePressed(MouseEvent me)
			{
				lblHelp.setForeground(Color.darkGray);
			}

			@Override
			public void mouseReleased(MouseEvent me)
			{
				lblHelp.setForeground(Color.black);
				OSUtils.browse("http://www.twitchapps.com/tmi/");
			}
		});

		this.btnSubmit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setState(LoginState.CONNECTING);
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						Controller.controller.connect("irc.twitch.tv", "#aylobot", LoginFrame.this.txtUsername.getText(), LoginFrame.this.pwdOAuthToken.getPassword());
					}
				}, "Connect Thread").start();
			}
		});
	}

	public void setState(final LoginState state)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				switch (state)
				{
					case CONNECTING:
						LoginFrame.this.btnSubmit.setEnabled(false);
						LoginFrame.this.btnSubmit.setText("Connecting...");
						break;
					case LOGIN_FAIL:
						JOptionPane.showMessageDialog(LoginFrame.this, "Login failed! Check your login credentials.", "", JOptionPane.PLAIN_MESSAGE);
						LoginFrame.this.btnSubmit.setEnabled(true);
						LoginFrame.this.btnSubmit.setText("Submit");
						break;
					case LOGIN_SUCCESS:
						LoginFrame.this.btnSubmit.setEnabled(true);
						LoginFrame.this.btnSubmit.setText("Submit");
						break;
					case LOGIN_TIMEOUT:
						JOptionPane.showMessageDialog(LoginFrame.this, "Login timed out! Try again.", "", JOptionPane.PLAIN_MESSAGE);
						LoginFrame.this.btnSubmit.setEnabled(true);
						LoginFrame.this.btnSubmit.setText("Submit");
						break;
					default:
						break;
				}
			}
		});
	}
}
