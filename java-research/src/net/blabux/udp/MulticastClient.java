package net.blabux.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient {

	public static void main(String[] args) {
		try {
			new MulticastClient().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void run() throws Exception {
		byte[] buffer = new byte[256];
		MulticastSocket socket = new MulticastSocket(4446);
		InetAddress group = InetAddress.getByName("192.168.1.255");
		try {
			while (!Thread.currentThread().isInterrupted()) {
				DatagramPacket packet = new DatagramPacket(buffer,
						buffer.length);
				socket.receive(packet);
				System.out.println("Received: " + new String(buffer));
				System.out.println("From: " + packet.getAddress());
			}
		} finally {
			socket.leaveGroup(group);
			socket.close();
		}
	}

}
