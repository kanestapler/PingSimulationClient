import java.net.*;
import java.io.*;

public class PingThread extends Thread {
	private int threadNum;


	private int firstPort = 2000;
	private int secondPort = 2001;
	
	public PingThread(int val){
		threadNum = val;
	}

	public void run() {
		//use localhost to experiment on a standalone computer
		String hostname="localhost";    String message = Integer.toString(threadNum);
		try {
			// Create a datagram socket, look for the first available port
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket();

			ByteArrayOutputStream bOut = new ByteArrayOutputStream();
			PrintStream pOut = new PrintStream(bOut);
			pOut.print(message);
			//convert printstream to byte array
			byte [] bArray = bOut.toByteArray();
			// Create a datagram packet, containing a maximum buffer of 256 bytes
			DatagramPacket packet=new DatagramPacket( bArray, bArray.length );
			
			//get the InetAddress object
			InetAddress remote_addr = InetAddress.getByName(hostname);
			//check its IP number
			//configure the DataGramPacket
			packet.setAddress(remote_addr);
			packet.setPort(firstPort);
			//send the packet
			socket.send(packet);
			long sentTime = System.currentTimeMillis();
			@SuppressWarnings("resource")
			DatagramSocket recieveSocket = new DatagramSocket(secondPort+threadNum);
			DatagramPacket receivePacket = new DatagramPacket( new byte[256], 256 );
			recieveSocket.receive(receivePacket);
			long receiveTime = System.currentTimeMillis();
			long totalTime = receiveTime - sentTime;
			System.out.println("Ping Time: " + totalTime + "ms");

		}
		catch (UnknownHostException ue){
			System.out.println("Unknown host "+hostname);
		}
		catch (IOException e)	{
			System.out.println ("Error - " + e);
		}
	}
}
