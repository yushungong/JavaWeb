package net.com.gong.mina.myclient;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyClientHandler extends IoHandlerAdapter{
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		sessionClosed(session);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		super.inputClosed(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("********客户端**********");
		System.out.println("服务器响应:"+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, "ok");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
	}
}
