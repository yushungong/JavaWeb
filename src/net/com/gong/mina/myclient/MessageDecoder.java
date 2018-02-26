package net.com.gong.mina.myclient;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MessageDecoder extends CumulativeProtocolDecoder {


	private String charset = "UTF-8";

	public MessageDecoder(String charset) {
		this.charset = charset;
	}

	@Override
	protected synchronized boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		byte[] rqByte = new byte[in.remaining()];
		in.get(rqByte);
		in.mark();
		String request = new String(rqByte,charset);
		if(request.startsWith("##") && request.endsWith("##")){
			out.write(request.replaceAll("##",""));
			return false;
		}
		System.out.println("数据报文头尾不完整");
		in.reset();
		return true;
	}
}
