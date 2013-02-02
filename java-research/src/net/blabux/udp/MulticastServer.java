package net.blabux.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

public class MulticastServer {

	public static void main(String[] args) {
		try {
			new MulticastServer().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() throws Exception {
		DatagramSocket socket = new MulticastSocket();
		//socket.setBroadcast(true);
		SocketAddress address = new InetSocketAddress("192.168.1.255", 4446);
		try {
			while (!Thread.currentThread().isInterrupted()) {
				DatagramPacket packet = createPacket("simple", address);
				socket.send(packet);
				System.out.println("sent:" + packet);
				Thread.sleep(1000);
			}
		} finally {
			socket.close();
		}

	}

	private DatagramPacket createPacket(String msg, SocketAddress address) throws Exception {
		byte[] bytes=msg.getBytes();
		return new DatagramPacket(bytes, bytes.length, address);
	}

}
