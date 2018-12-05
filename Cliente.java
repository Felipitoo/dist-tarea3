/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

/**
 *
 * @author Inception10
 */
// A Java program for a Client 
import java.net.*; 
import java.io.*; 

public class Cliente 
{ 
	// initialize socket and input output streams 
	private Socket socket		 = null; 
	private String input = null; 
	private DataOutputStream out	 = null; 

	// constructor to put ip address and port 
	public Cliente(String address, int port) 
	{ 
		// establish a connection 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{ 
			socket = new Socket(address, port); 
			System.out.println("Connected"); 

			// takes input from terminal 
			input = br.readLine(); 

			// sends output to the socket 
			out = new DataOutputStream(socket.getOutputStream()); 
		} 
		catch(UnknownHostException u) 
		{ 
			System.out.println(u); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 

		// string to read message from input 
		String line = ""; 

		// keep reading until "Over" is input 
		while (!line.equals("Over")) 
		{ 
			try
			{ 
				line = br.readLine(); 
				out.writeUTF(line); 
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} 

		// close the connection 
		try
		{ 
			out.close(); 
			socket.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 
 
} 
