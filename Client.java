package clientServer;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	int port = 8000;
	String host = "localhost";
	DataInputStream in;
	DataOutputStream out;
	Socket socket;
	double aNumber = 5;

	
	void runme () {
		Scanner sc = new Scanner(System.in);
		
		try {
			// Define client socket, and initialize in and out streams.
			socket = new Socket(host, port);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			Double msg;
			while (true) {
				try {
					// ask user to enter a double
					System.out.print("Enter a number to send to server: ");
					msg = sc.nextDouble();
				} catch (Exception e) {
					System.out.println("Try again.");
					continue;
				}
				
				// send the double to the server
				out.writeDouble(msg);
				out.flush();
				
				// read the server's response, and print it out.
				System.out.println("Client: The server says the square is: " + 
						in.readDouble());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
