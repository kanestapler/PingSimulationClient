import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader bufR = new BufferedReader(in);
		String message = "";
		while (true) {
			try {
				//create a socket and connect
				Socket socket = new Socket("localhost", 4400);
				System.out.print("Enter a MESSAGE: ");
				message = bufR.readLine();

				socket.setSendBufferSize(message.length());

				//connect to a buffered reader
				BufferedOutputStream bos = new
						BufferedOutputStream(socket.getOutputStream(),
								socket.getSendBufferSize());
				for (int i=0; i<message.length(); i++){
					bos.write((int) message.charAt(i));}

				bos.close();
				if (message.equals("bye")){
					System.out.println("Closing.... goodbye!");
					break;
				}

				socket.close();
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
		}
	}

}