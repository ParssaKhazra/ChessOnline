package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import com.dosse.upnp.UPnP;

import packets.JoinPacket;



public class Host extends Thread{
	private boolean running = true;


	
	public Host(){
	
		
	}
	public void run(){
		
		
		
		
	}
	private void parsePacket(byte[] data, InetAddress address, int port) {
	
		
	}
	public void addConnection(Client client, JoinPacket packet) {
	
		
	}

	public void sendData(byte[] data,InetAddress ipAddress,int port){
		
	}
	public void sendToAll(byte[] data) {
	
	}
}


