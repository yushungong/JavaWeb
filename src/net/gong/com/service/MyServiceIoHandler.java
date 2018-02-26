package net.gong.com.service;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyServiceIoHandler extends IoHandlerAdapter{

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		/*这个方法在你的程序、Mina 自身出现异常时回调，一般这里是关闭IoSession。*/
		sessionClosed(session);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		//输入关闭时
		super.inputClosed(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		/*
		 * 接收到消息时调用的方法，也就是用于接收消息的方法，一般情况下，message 是一个IoBuffer 类，如果你使用了协议编解码器，
		 * 那么可以强制转换为你需要的类型。通常我们都是会使用协议编解码器的， 就像上面的例子， 因为协议编解码器是
		 * TextLineCodecFactory，所以我们可以强制转message 为String 类型。
		 */
		System.out.println("*******服务端********");
		System.out.println("客户端请求："+message.toString());
		//session.write("JC0940010010002013-03-27 22:00:000033@@@JZ12|SO2|2013-03-27 22:01:002013-03-27 23:01:00|1|1tek24####");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		/*
		 * 当发送消息成功时调用这个方法，注意这里的措辞，发送成功之后，也就是说发送消息是不能用这个方法的。 发送消息的时机：
		 * 发送消息应该在sessionOpened()、messageReceived()方法中调用IoSession.write()方法完成。
		 * 因为在sessionOpened()方法中，TCP 连接已经真正打开，同样的在messageReceived()方法TCP
		 * 连接也是打开状态，只不过两者的时机不同。 sessionOpened()方法是在TCP
		 * 连接建立之后，接收到数据之前发送；messageReceived()方法是在接收到数据之后发送，
		 * 你可以完成依据收到的内容是什么样子，决定发送什么样的数据
		 * 。因为这个接口中的方法太多，因此通常使用适配器模式IoHandlerAdapter，覆盖你所感兴趣的方法即可。
		 */
		super.messageSent(session, "ok");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		/* 对于TCP 来说，连接被关闭时，调用这个方法。对于UDP 来说，IoSession 的close()方法被调用时才会毁掉这个方法。 */
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		/*
		 * 对于TCP 连接来说，连接被接受的时候调用，但要注意此时TCP 连接并未建立，此方法仅代表字面含义， 也就是连接的对象IoSession
		 * 被创建完毕的时候，回调这个方法。 对于UDP 来说，当有数据包收到的时候回调这个方法，因为UDP 是无连接的。
		 */
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		/* 这个方法在IoSession 的通道进入空闲状态时调用，对于UDP 协议来说，这个方法始终不会被调用。 */
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		/*
		 * 这个方法在连接被打开时调用，它总是在sessionCreated()方法之后被调用。 对于TCP
		 * 来说，它是在连接被建立之后调用，你可以在这里执行一些认证操作、发送数据等。 对于UDP
		 * 来说，这个方法与sessionCreated()没什么区别，但是紧跟其后执行。
		 * 如果你每隔一段时间，发送一些数据，那么sessionCreated
		 * ()方法只会在第一次调用，但是sessionOpened()方法每次都会调用。
		 */
		super.sessionOpened(session);
	}
}
