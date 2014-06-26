package com.moomoohk.JillBotController;

import java.io.IOException;

import org.pircbotx.Configuration.Builder;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.NoticeEvent;

import com.moomoohk.JillBotController.LoginFrame.LoginState;

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

	public void connect(String network, String room, String username, char[] password)
	{
		this.username = username;
		System.out.println("Connecting...");
		bot = new PircBotX(new Builder<>().setName(username).addListener(controller).setServerHostname("irc.twitch.tv").addAutoJoinChannel("#aylojill").setServerPassword(new String(password)).buildConfiguration());
		try
		{
			bot.startBot();
		}
		catch (IOException | IrcException e)
		{
			e.printStackTrace();
		}
	}

	//	public void disconnect()
	//	{
	//		this.conman.quit();
	//	}

	//	@Override
	//	public void receiveEvent(IRCEvent e)
	//	{
	//		System.out.println(e.getType() + " " + e.getRawEventData());
	//		switch (e.getType())
	//		{
	//			case NOTICE:
	//				NoticeEvent ne = (NoticeEvent) e;
	//				if (ne.byWho().equals("tmi.twitch.tv") && ne.getNoticeMessage().equals("Login unsuccessful"))
	//					loginFrame.setState(LoginState.LOGIN_FAIL);
	//				break;
	//			case CONNECT_COMPLETE:
	//				System.out.println("Connected to server.");
	//				this.s = e.getSession();
	//				System.out.println("Joining room...");
	//				this.s.join(this.room);
	//				break;
	//			case JOIN_COMPLETE:
	//				JoinCompleteEvent jce = (JoinCompleteEvent) e;
	//				this.channel = jce.getChannel();
	//				System.out.println("Connected to " + this.channel.getName());
	//				break;
	//			case PRIVATE_MESSAGE:
	//				MessageEvent me2 = (MessageEvent) e;
	//				System.out.println("PM[" + me2.getNick() + ": " + me2.getMessage() + "]");
	//				break;
	//			case CHANNEL_MESSAGE:
	//				MessageEvent me = (MessageEvent) e;
	//				System.out.println(me.getMessage());
	//				break;
	//			case CTCP_EVENT:
	//				CtcpEvent ctcpe = (CtcpEvent) e;
	//				System.out.println("CTCP message: " + ctcpe.getCtcpString()); //VERSION TIME PING
	//				break;
	//			case DEFAULT:
	//				break;
	//			default:
	//				System.out.println(e.getType() + " " + e.getRawEventData());
	//				break;
	//		}
	//	}

	//	public void send(String message)
	//	{
	//		if (message == null || message.length() == 0)
	//			return;
	//		if (this.channel != null)
	//			this.channel.say(message);
	//		else
	//			System.out.println("No channel!");
	//	}
	//
	//	public void message(String nick, String message)
	//	{
	//		Scanner scanner = new Scanner(message);
	//		while (scanner.hasNextLine())
	//			this.s.sayPrivate(nick, scanner.nextLine());
	//		scanner.close();
	//	}

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
			bot.stopBotReconnect();
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
		bot.stopBotReconnect();
		loginFrame.setState(LoginState.LOGIN_FAIL);
	}
}
