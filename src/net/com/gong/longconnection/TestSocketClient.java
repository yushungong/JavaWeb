package net.com.gong.longconnection;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.CharBuffer;

public class TestSocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestSocketClient().start();
	}
	
	public void start() {
		try {
			Socket socket = new Socket("127.0.0.1", 18889);
			new SendThread(socket).start();
			new ReceiveThread(socket).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	class SendThread extends Thread {
		private Socket socket;

		public SendThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					String send = "<SOAP-ENV:Envelope>" + System.currentTimeMillis() + "</SOAP-ENV:Envelope>";
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					pw.write(send);
					pw.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	class ReceiveThread extends Thread {
		private Socket socket;

		public ReceiveThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Reader reader = new InputStreamReader(socket.getInputStream());
					CharBuffer charbuffer = CharBuffer.allocate(8192);
					int index;
					while ((index = reader.read(charbuffer)) != -1) {
						charbuffer.flip();// 设置从0到刚刚读取到的位置
						System.out.println("client:" + charbuffer.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}