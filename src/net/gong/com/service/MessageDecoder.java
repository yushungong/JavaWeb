package net.gong.com.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MessageDecoder extends CumulativeProtocolDecoder {

	private String charset;

	public MessageDecoder(String charset) {
		this.charset = charset;
	}

	@Override
	protected synchronized boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int length = in.remaining();
		byte[] lenBytes = new byte[length];
		in.get(lenBytes, 0, length);// 读取2个字节
		String recString = new String(lenBytes, "UTF-8");
		System.out.println(recString);
		
		/*byte[] lenBytes = new byte[4];
		in.get(lenBytes, 0, 4);// 读取2个字节
		String recString = new String(lenBytes, "UTF-8");
		System.out.println(recString);
		
		byte[] leng = new byte[10];
		in.get(leng, 0, 10);// 读取2个字节
		String lengt = new String(leng, "UTF-8");
		System.out.println(lengt);
		
		byte[] by = new byte[19];
		in.get(by, 0,19);// 读取2个字节
		String strs = new String(by, "UTF-8");
		System.out.println(strs);
		
		byte[] end = new byte[4];
		in.get(end, 0,4);// 读取2个字节
		String ends = new String(end, "UTF-8");
		System.out.println(ends);
		
		byte[] end2 = new byte[2];
		in.get(end2, 0,2);// 读取2个字节
		String ends2 = new String(end2, "UTF-8");
		System.out.println(ends2);
		
		byte[] end22 = new byte[2];
		in.get(end22, 0,2);// 读取2个字节
		String ends22 = new String(end22, "UTF-8");
		System.out.println(ends22);
		
		byte[] end23 = new byte[3];
		in.get(end23, 0,3);// 读取2个字节
		String ends23 = new String(end23, "UTF-8");
		System.out.println(ends23);
		
		byte[] bbs = new byte[Integer.parseInt(ends,16)];
		in.get(bbs, 0,Integer.parseInt(ends,16));// 读取2个字节
		String str = new String(bbs, "UTF-8");
		System.out.println(str);
		byte[] bbb = hexStrToByteArr(str);
		System.out.println(new String(uncompress(bbb)));*/
		
		/*byte[] bbs = new byte[Integer.parseInt(ends,16)];
		in.get(bbs, 0,Integer.parseInt(ends,16));// 读取2个字节
		String str = new String(bbs, "UTF-8");
		System.out.println(str);*/
		
		/*byte[] end24 = new byte[3];
		in.get(end24, 0,3);// 读取2个字节
		String ends24 = new String(end24, "UTF-8");
		System.out.println(ends24);
		
		byte[] end224 = new byte[2];
		in.get(end224, 0,2);// 读取2个字节
		String ends224 = new String(end224, "UTF-8");
		System.out.println(ends224);
		
		byte[] end2245 = new byte[4];
		in.get(end2245, 0,4);// 读取2个字节
		String ends2245 = new String(end2245, "UTF-8");
		System.out.println(ends2245);*/
		
		out.write("解析完成");
		if (in.remaining() > 0) {
			System.out.println("粘包长度 package left=" + in.remaining() + " data=" + in.toString());
			return true;
		}
		return false;
		/*int length = in.remaining();
		byte[] lenBytes = new byte[length];
		in.get(lenBytes, 0, length);// 读取2个字节
		String recString = new String(lenBytes, charset);
		String request = new String(recString.getBytes("GB2312"),"UTF-8");
		String str = request.split("####")[0];
		String str2 = str.split("@@@")[1];
		String str3 = str2.split("tek")[0];
		String str4 = decompress1(str3);
		out.write(str4);
		return true;*/
		/*if (in.remaining() >= 18) {
			in.mark();
			byte[] lenBytes = new byte[2];
			in.get(lenBytes, 0, 2);// 读取2个字节
			String recString = new String(lenBytes, charset);
			if ("##".equals(recString)) {
				byte[] len = new byte[4];
				in.get(len, 0, 4);
				// 得出正文总长度
				int length = Integer.parseInt(new String(len, charset));
				if (in.remaining() >= (length + 9)) {
					byte[] buf = new byte[length];
					in.get(buf, 0, length);
					String str = new String(buf, charset);
					String string = decompress1(str);
					out.write(string);
					if (in.remaining() > 0) {
						log.warn("粘包长度 package left=" + in.remaining() + " data=" + in.toString());
					}
					return true;
				} else {
					log.warn("********** 数据报文不完整 **********  断包长度： " + in.remaining() + " 报文总长度： " + (length));
				}
			}
			in.reset();
		}*/
		//return false;
	}
	@SuppressWarnings("resource")
	public static byte[] uncompress(byte[] bytes) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(in);
			byte[] buffer = new byte[2048];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}
	//zipOutputStream解压方法
    @SuppressWarnings("unused")    
    public static final String decompress1(String bytestr) {  
    	byte[] paramArrayOfByte = null;
		try {
			paramArrayOfByte = hexStrToByteArr(bytestr);
		} catch (Exception e) {
			e.printStackTrace();
		}    
        ByteArrayOutputStream byteArrayOutputStream = null;    
        ByteArrayInputStream byteArrayInputStream = null;    
        ZipInputStream zipInputStream = null;    
        String str;    
        try {    
            byteArrayOutputStream = new ByteArrayOutputStream();    
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);    
            zipInputStream = new ZipInputStream(byteArrayInputStream);    
            ZipEntry localZipEntry = zipInputStream.getNextEntry(); 
            byte[] arrayOfByte = new byte[1024];    
            int i = -1;    
            while ((i = zipInputStream.read(arrayOfByte)) != -1)    
                byteArrayOutputStream.write(arrayOfByte, 0, i);    
            str = byteArrayOutputStream.toString("GB2312");    
        } catch (IOException localIOException7) {    
            str = null;    
        } finally {    
            if (zipInputStream != null)    
                try {    
                    zipInputStream.close();    
                } catch (IOException localIOException8) {    
                }    
            if (byteArrayInputStream != null)    
                try {    
                    byteArrayInputStream.close();    
                } catch (IOException localIOException9) {    
                }    
            if (byteArrayOutputStream != null)    
                try {    
                    byteArrayOutputStream.close();    
                } catch (IOException localIOException10) {    
            }    
        }    
        return str;    
    }
    //把压缩字符串转化为byte[]
    public static byte[] hexStrToByteArr(String str) throws Exception {
		byte[] arrB = str.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] byteArr = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			byteArr[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return byteArr;
	}
}
