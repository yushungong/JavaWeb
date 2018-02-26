package net.com.gong.mina.myservice;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class AccessDataService {
	public static void main(String[] args) {
		miasn();
	}
	private static void miasn() {
		Integer port = 12347;
		NioSocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		MyServiceIoHandler serverIoHandler = new MyServiceIoHandler();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.setBacklog(500);
		acceptor.getSessionConfig().setReuseAddress(true);
		/* 设置无延时发送 */
		acceptor.getSessionConfig().setTcpNoDelay(true);
		/* 设置接受信息缓冲区的大小 */
		acceptor.getSessionConfig().setReceiveBufferSize(5 * 1024);
		/* 设置读取信息缓冲区大小 */
		acceptor.getSessionConfig().setReadBufferSize(5 * 1024);
		/* 设置发送缓冲区大小 */
		acceptor.getSessionConfig().setSendBufferSize(1024);
		/* 设置空闲等待时间 *///读写 通道均在60*15秒内无任何操作就进入空闲状态
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60 * 15);
		/* 设置自定义报文编码格式 */
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MsgCodecFactory("UTF-8")));
		acceptor.setHandler(serverIoHandler);

		try {
			acceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
