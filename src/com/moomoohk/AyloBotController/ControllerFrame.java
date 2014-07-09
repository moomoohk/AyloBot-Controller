package com.moomoohk.AyloBotController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.moomoohk.Mootilities.OSUtils.OSUtils.OS;

/**
 * 
 * @author Meshulam Silk (moomoohk@ymail.com)
 * @since Jun 26, 2014
 */
public class ControllerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPane;
	private final JTextArea txtErrors;
	private final JButton btnForward, btnBack, btnLeft, btnRight, btnEcho;

	private final Timer errorTimer = new Timer(5000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ControllerFrame.this.txtErrors.setText("(Errors will show up here)");
		}
	});
	private final JLabel label;
	private final JLabel label_1;
	private final JLabel label_2;
	private final JLabel label_3;

	public ControllerFrame()
	{
		setTitle("AyloBot Controller by moomoohk");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(350, 340));
		setLocationRelativeTo(Controller.loginFrame);
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		this.contentPane.setLayout(sl_contentPane);

		ActionListener btnListener = new ActionListener()
		{
			@Override
			public void actionPerformed(final ActionEvent e)
			{
				new Thread(new Runnable()
				{
					@Override
					public void run()
					{
						String command = ((JButton) e.getSource()).getText();
						Controller.bot.sendIRC().message("#aylobot", command);
					}
				}, "Send Command Thread").start();
			}
		};

		JPanel buttonPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonPanel, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonPanel, -10, SpringLayout.EAST, this.contentPane);
		buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.btnLeft = new JButton("left");
		this.btnEcho = new JButton("echo");
		this.txtErrors = new JTextArea("(Errors will show up here)");
		sl_contentPane.putConstraint(SpringLayout.WEST, this.txtErrors, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.txtErrors, -10, SpringLayout.EAST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonPanel, 6, SpringLayout.SOUTH, this.txtErrors);
		this.txtErrors.setEditable(false);
		this.txtErrors.setLineWrap(true);
		this.txtErrors.setWrapStyleWord(true);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.txtErrors, -238, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.txtErrors, 5, SpringLayout.NORTH, this.contentPane);
		this.contentPane.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));

		this.label = new JLabel("");
		buttonPanel.add(this.label);
		this.btnForward = new JButton("forward");

		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnForward, 149, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnForward, 121, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnForward, -148, SpringLayout.EAST, this.contentPane);
		this.btnForward.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.BOLD, 16));
		buttonPanel.add(this.btnForward);
		this.btnRight = new JButton("right");

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnEcho, 28, SpringLayout.SOUTH, this.btnForward);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnRight, 1, SpringLayout.SOUTH, this.btnForward);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnForward, 6, SpringLayout.SOUTH, this.txtErrors);

		this.btnForward.addActionListener(btnListener);

		this.label_1 = new JLabel("");
		buttonPanel.add(this.label_1);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnLeft, 134, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnLeft, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnLeft, -51, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnLeft, -334, SpringLayout.EAST, this.contentPane);
		this.btnLeft.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.BOLD, 16));
		buttonPanel.add(this.btnLeft);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnEcho, 112, SpringLayout.WEST, this.contentPane);
		this.btnEcho.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.BOLD, 16));
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnEcho, 149, SpringLayout.EAST, this.btnLeft);
		buttonPanel.add(this.btnEcho);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnRight, -74, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnRight, -47, SpringLayout.EAST, this.contentPane);
		this.btnRight.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.BOLD, 16));
		buttonPanel.add(this.btnRight);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnRight, 21, SpringLayout.EAST, this.btnEcho);
		this.btnRight.addActionListener(btnListener);
		this.txtErrors.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.txtErrors.setForeground(Color.RED);
		this.txtErrors.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));
		this.contentPane.add(this.txtErrors);

		JButton btnQuit = new JButton("Quit");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonPanel, -10, SpringLayout.NORTH, btnQuit);
		btnQuit.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.PLAIN, 13));

		this.label_2 = new JLabel("");
		buttonPanel.add(this.label_2);
		this.btnBack = new JButton("back");

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnBack, 212, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnEcho, -6, SpringLayout.NORTH, this.btnBack);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnBack, 28, SpringLayout.EAST, this.btnLeft);
		this.btnBack.setFont(new Font(Controller.os == OS.MACOSX ? "PT Sans" : "Calibri", Font.BOLD, 16));
		buttonPanel.add(this.btnBack);
		this.btnBack.addActionListener(btnListener);

		this.label_3 = new JLabel("");
		buttonPanel.add(this.label_3);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnQuit, 271, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnQuit, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnQuit, -10, SpringLayout.EAST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnQuit, -10, SpringLayout.SOUTH, this.contentPane);
		this.contentPane.add(btnQuit);

		btnQuit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		this.btnLeft.addActionListener(btnListener);
		this.btnEcho.addActionListener(btnListener);
	}

	public void error(String message)
	{
		this.txtErrors.setText(message);
		this.errorTimer.restart();
	}
}
