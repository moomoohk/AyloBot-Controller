package com.moomoohk.JillBotController;

import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.NoticeEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import com.moomoohk.JillBotController.LoginFrame.LoginState;
import com.moomoohk.Mootilities.OSUtils.OSUtils;
import com.moomoohk.Mootilities.OSUtils.OSUtils.OS;

/**
 * 
 * @author Meshulam Silk (moomoohk@ymail.com)
 * @since Jun 26, 2014
 */
public class Controller extends ListenerAdapter<PircBotX>
{
	public static Controller controller;
	public static LoginFrame loginFrame;
	public static ControllerFrame controllerFrame;
	public static PircBotX bot;

	public String username;

	public static OS os = OSUtils.getCurrentOS();

	public void connect(String network, String room, String username, char[] password)
	{
		this.username = username;
		System.out.println("Connecting...");
		bot = new PircBotX(new Builder<PircBotX>().setName(username).addListener(controller).setServerHostname("irc.twitch.tv").addAutoJoinChannel("#aylojill").setServerPassword(new String(password)).buildConfiguration());
		try
		{
			bot.startBot();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		controller = new Controller();
		loginFrame = new LoginFrame();
		controllerFrame = new ControllerFrame();

		loginFrame.setVisible(true);
	}

	@Override
	public void onEvent(Event<PircBotX> e) throws Exception
	{
		super.onEvent(e);
		System.out.println(e.toString());
	}

	@Override
	public void onNotice(NoticeEvent<PircBotX> ne)
	{
		if (ne.getNotice().equals("Login unsuccessful"))
		{
			bot.sendIRC().quitServer();
			loginFrame.setState(LoginState.LOGIN_FAIL);
		}
	}

	@Override
	public void onConnect(ConnectEvent<PircBotX> ce)
	{
		loginFrame.setState(LoginState.LOGIN_SUCCESS);
		loginFrame.setVisible(false);
		controllerFrame.setVisible(true);
	}

	@Override
	public void onDisconnect(DisconnectEvent<PircBotX> de)
	{
		bot.sendIRC().quitServer();
		bot.stopBotReconnect();
		loginFrame.setState(LoginState.LOGIN_FAIL);
	}

	@Override
	public void onPrivateMessage(PrivateMessageEvent<PircBotX> pme)
	{
		System.out.println(pme.getUser().getNick() + " " + pme.getMessage());
		if (pme.getUser().getNick().equals("jtv"))
			controllerFrame.error(pme.getMessage());
	}
}
