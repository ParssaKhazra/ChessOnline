package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import com.dosse.upnp.UPnP;

import packets.JoinPacket;



public class host extends Thread{
	private boolean running = true;
	  private static final int PORT = 4014;
	  private  ServerSocket ss;
	
	public host(){
	
		System.out.println("Opening socket...");
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Attempting UPnP port forwarding...");
        if (UPnP.isUPnPAvailable()) { //is UPnP available?
            if (UPnP.isMappedTCP(PORT)) { //is the port already mapped?
                System.out.println("UPnP port forwarding not enabled: port is already mapped");
            } else if (UPnP.openPortTCP(PORT)) { //try to map port
                System.out.println("UPnP port forwarding enabled");
            } else {
                System.out.println("UPnP port forwarding failed");
            }
        } else {
            System.out.println("UPnP is not available");
        }
		
	}
	public void run(){
		
		 for (;;) {
             try {
                 Socket s = ss.accept(); //wait for connections on socket
                 
                 System.out.println("Incoming connection from " + s.getInetAddress().getHostAddress()); 
                 s.close(); //close the connection
             } catch (Throwable t) {
                 System.err.println("Network error: "+t);
             }
         }
     }
		
		



	
}


