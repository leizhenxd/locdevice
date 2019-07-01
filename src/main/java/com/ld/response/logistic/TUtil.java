package com.ld.response.logistic;

import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TUtil {

	private final static int PORT = 5500;
	private static final String HOSTNAME = "106.15.191.245";

	private static Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();

	public static void main(String[] args) {
		getAllProbe();
		getPosition(3530086);

	}

	public static List<Integer> getAllProbe() {
		List<Integer> result = new ArrayList<Integer>();
		try {
			DatagramSocket socket = new DatagramSocket(PORT);
			socket.setSoTimeout(2000);
			InetAddress host = InetAddress.getByName(HOSTNAME);
			//指定包要发送的目的地
			DatagramPacket request = new DatagramPacket(new byte[]{0x08}, 1, host, PORT);
			request.setData(new byte[]{0x08});
			//为接受的数据包创建空间
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			socket.send(request);
			socket.receive(response);
			String hexString = bytesToHexString(response.getData(), response.getLength());
			int num = Integer.parseInt(byteFormat(hexString,1), 16);
			for(int i=1; i<=num; i++) {
				Integer key = Integer.parseInt(byteFormat(hexString,i+1), 16);
				byte[] bs = new byte[4];
				int k=0;
				for(int j=i*4;j<i*4+4; j++) {
					bs[k] = response.getData()[j];
					bs[k] = response.getData()[j];
					k++;
				}
				map.put(key, bs);
				result.add(key);
				System.out.println(key);
			}
			System.out.println(num);
			socket.close();
		} catch (IOException e) {
			return getAllProbe();
		}
		return result;
	}

	public static ProbePosition getPosition (Integer id) {
		ProbePosition p = null;
		try  {
			DatagramSocket socket = new DatagramSocket(PORT);
			socket.setSoTimeout(2000);
			InetAddress host = InetAddress.getByName(HOSTNAME);
			//指定包要发送的目的地
			byte[] bs = new byte[5];
			bs[0] = 0x09;
			for(int i=0;i<map.get(id).length;i++) {
				bs[i+1] = map.get(id)[i];
			}

			DatagramPacket request = new DatagramPacket(bs, 1, host, PORT);
			//为接受的数据包创建空间
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			request.setData(bs);
			socket.send(request);
			socket.receive(response);
			String hexString = bytesToHexString(response.getData(), response.getLength());
			int num = Integer.parseInt(byteFormat(hexString,1), 16);
			if(num > 0) {
				p = new ProbePosition();
				p.setLng(0.0001f*new BigInteger(byteFormat(hexString,4), 16).intValue()/60);
				p.setLat(0.0001f*new BigInteger(byteFormat(hexString,5), 16).intValue()/60);
				System.out.print(Integer.parseInt(byteFormat(hexString,2), 16)+"-");
				System.out.print(Integer.parseInt(byteFormat(hexString,3), 16)+"-");
				System.out.print(0.0001*new BigInteger(byteFormat(hexString,4), 16).intValue()/60+"-");
				System.out.println(0.0001*new BigInteger(byteFormat(hexString,5), 16).intValue()/60);
			}
			socket.close();
		} catch (IOException e) {
			return getPosition(id);
		}
		return p;
	}
	public static List<TempHistory> getHistory (Integer id, byte row) {
		List<TempHistory> result = new ArrayList<TempHistory>();
		try {
			DatagramSocket socket = new DatagramSocket(PORT);
			socket.setSoTimeout(2000);
			InetAddress host = InetAddress.getByName(HOSTNAME);
			//指定包要发送的目的地
			byte[] bs = new byte[13];
			bs[0] = 0x10;
			for(int i=0;i<map.get(id).length;i++) {
				bs[i+1] = map.get(id)[i];
			}
			bs[5]=row;bs[6]=0x00;bs[7]=0x00;bs[8]=0x00;
			bs[9]=0x00;bs[10]=0x00;bs[11]=0x00;bs[12]=0x00;

			DatagramPacket request = new DatagramPacket(bs, 1, host, PORT);
			//为接受的数据包创建空间
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			request.setData(bs);
			socket.send(request);
			socket.receive(response);
			String hexString = bytesToHexString(response.getData(), response.getLength());
			int num = Integer.parseInt(byteFormat(hexString,1), 16);
			for(int i=0; i< Integer.parseInt(byteFormat(hexString,1), 16)/4;i++){
				TempHistory th = new TempHistory();
				th.setId(Integer.parseInt(byteFormat(hexString,2+4*i), 16));
				th.setDate(getDate(Long.parseLong(byteFormat(hexString,3+4*i), 16)));
				th.setTemp(0.0625f*Integer.parseInt(byteFormat(hexString,4+4*i), 16));
				result.add(th);
				System.out.print(Integer.parseInt(byteFormat(hexString,2+4*i), 16)+"-");
				System.out.print(getDate(Long.parseLong(byteFormat(hexString,3+4*i), 16))+"-");
				System.out.print(0.0625*Integer.parseInt(byteFormat(hexString,4+4*i), 16)+"-");
				System.out.println(Integer.parseInt(byteFormat(hexString,5+4*i), 16));
			}

			socket.close();
		} catch (IOException e) {
			return getHistory(id, row);
		}
		Collections.reverse(result);
		return result;
	}



	public static String getDate(Long utc) {
		long sec = (utc&0xffff)<<1;
		long hour = sec/3600;
		long mm = (sec%3600)/60;
		long second = sec%60;
		utc = utc >> 16;
		long day = utc >> 11;
		long month = (utc>>7)&0xf;
		long year = utc&0x7f;
		return "20"+year+"-"+month+"-"+day+" " + hour+":"+mm+":"+second;
	}

	public static String byteFormat(String src, int position) {
		src = src.substring(8*(position-1), 8*position);
		String[] bytes = new String[4];
		for(int i=0; i<4; i++) {
			bytes[i] = src.substring(i*2,2*(i+1));
		}
		StringBuffer sb = new StringBuffer();
		for(int i=3; i>=0; i--) {
			sb.append(bytes[i]);
		}
		return sb.toString();
	}
	public static final String bytesToHexString(byte[] bArray, int length) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
}
