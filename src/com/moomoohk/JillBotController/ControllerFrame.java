package com.moomoohk.JillBotController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * 
 * @author Meshulam Silk (moomoohk@ymail.com)
 * @since Jun 26, 2014
 */
public class ControllerFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private final JPanel contentPane;
	private final JLabel lblErrors;
	private final JButton btnForward, btnBack, btnLeft, btnRight, btnEcho;

	private final Timer errorTimer = new Timer(5000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			ControllerFrame.this.lblErrors.setText("(Errors will show up here)");
		}
	});

	public ControllerFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(450, 340));
		setLocationRelativeTo(Controller.loginFrame);
		setResizable(false);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		this.contentPane.setLayout(sl_contentPane);

		JPanel buttonPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonPanel, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonPanel, -10, SpringLayout.EAST, this.contentPane);
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.btnForward = new JButton("forward");
		this.btnLeft = new JButton("left");
		this.btnRight = new JButton("right");
		this.btnEcho = new JButton("echo");
		this.btnBack = new JButton("back");
		this.lblErrors = new JLabel("(Errors will show up here)");
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonPanel, 6, SpringLayout.SOUTH, this.lblErrors);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.lblErrors, -238, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, this.lblErrors, 5, SpringLayout.NORTH, this.contentPane);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(buttonPanel);

		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnForward, 149, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnForward, 121, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnForward, -148, SpringLayout.EAST, this.contentPane);
		this.btnForward.setFont(new Font("PT Sans", Font.BOLD, 16));
		buttonPanel.add(this.btnForward, BorderLayout.NORTH);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnLeft, 134, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnLeft, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnLeft, -51, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnLeft, -334, SpringLayout.EAST, this.contentPane);
		this.btnLeft.setFont(new Font("PT Sans", Font.BOLD, 16));
		buttonPanel.add(this.btnLeft, BorderLayout.WEST);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnRight, 1, SpringLayout.SOUTH, this.btnForward);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnRight, -74, SpringLayout.SOUTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnRight, -47, SpringLayout.EAST, this.contentPane);
		this.btnRight.setFont(new Font("PT Sans", Font.BOLD, 16));
		buttonPanel.add(this.btnRight, BorderLayout.EAST);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnEcho, 28, SpringLayout.SOUTH, this.btnForward);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnEcho, 112, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnRight, 21, SpringLayout.EAST, this.btnEcho);
		this.btnEcho.setFont(new Font("PT Sans", Font.BOLD, 16));
		sl_contentPane.putConstraint(SpringLayout.EAST, this.btnEcho, 149, SpringLayout.EAST, this.btnLeft);
		buttonPanel.add(this.btnEcho, BorderLayout.CENTER);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnBack, 212, SpringLayout.NORTH, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, this.btnEcho, -6, SpringLayout.NORTH, this.btnBack);
		sl_contentPane.putConstraint(SpringLayout.WEST, this.btnBack, 28, SpringLayout.EAST, this.btnLeft);
		this.btnBack.setFont(new Font("PT Sans", Font.BOLD, 16));
		buttonPanel.add(this.btnBack, BorderLayout.SOUTH);

		sl_contentPane.putConstraint(SpringLayout.NORTH, this.btnForward, 6, SpringLayout.SOUTH, this.lblErrors);
		this.lblErrors.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.lblErrors.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblErrors.setForeground(Color.RED);
		this.lblErrors.setFont(new Font("PT Sans", Font.PLAIN, 13));
		sl_contentPane.putConstraint(SpringLayout.WEST, this.lblErrors, 10, SpringLayout.WEST, this.contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, this.lblErrors, 430, SpringLayout.WEST, this.contentPane);
		this.contentPane.add(this.lblErrors);

		JButton btnQuit = new JButton("Quit");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonPanel, -10, SpringLayout.NORTH, btnQuit);
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
						Controller.bot.sendIRC().message("#aylojill", command);
					}
				}, "Send Command Thread").start();
			}
		};

		this.btnForward.addActionListener(btnListener);
		this.btnBack.addActionListener(btnListener);
		this.btnLeft.addActionListener(btnListener);
		this.btnRight.addActionListener(btnListener);
		this.btnEcho.addActionListener(btnListener);
	}

	public void error(String message)
	{
		this.lblErrors.setText(message);
		this.errorTimer.restart();
	}
}
