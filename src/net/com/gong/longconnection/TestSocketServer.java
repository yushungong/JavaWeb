package net.com.gong.longconnection;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

public class TestSocketServer {

	private final static String SOAP_BEGIN = "<SOAP-ENV:Envelope>";
	private final static String SOAP_END = "</SOAP-ENV:Envelope>";

	public static void main(String[] args) {
		TestSocketServer testserver = new TestSocketServer();
		testserver.start();
	}

	public void start() {
		try {
			ServerSocket serversocket = new ServerSocket(18889);
			while (true) {
				Socket socket = serversocket.accept();
				new SocketThread(socket).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class SocketThread extends Thread {
		private Socket socket;
		private String temp;

		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		public Socket getsocket() {
			return this.socket;
		}

		public void setsocjet(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				Reader reader = new InputStreamReader(socket.getInputStream());
				Writer writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"));
				//字符缓冲区
				//allocate(int capacity):分配新的字符缓冲区,capacity - 新缓冲区的容量，以字符为单位
				CharBuffer charbuffer = CharBuffer.allocate(8192);
				int readindex = -1;
				while ((readindex = reader.read(charbuffer)) != -1) {
					charbuffer.flip();
					temp += charbuffer.toString();
					System.out.println(temp);
					if (temp.indexOf(SOAP_BEGIN) != -1 && temp.indexOf(SOAP_END) != -1) {
						// System.out.println(new
						// Date().toLocaleString()+"server:"+temp);
						temp = "";
						writer.write("receive the soap message hahahah");
						writer.flush();
					} else if (temp.indexOf(SOAP_BEGIN) != -1) {
						temp = temp.substring(temp.indexOf(SOAP_BEGIN));
					}
					if (temp.length() > 1024 * 16) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						if (!socket.isClosed()) {
							socket.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
