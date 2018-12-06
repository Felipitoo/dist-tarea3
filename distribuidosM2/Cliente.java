/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos2;
import java.net.*;
import java.io.*;
/**
 *
 * @author Inception10
 */
public class Cliente implements Runnable {
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null;
    private Integer port;
    private String address;
    
    public Cliente(String addr, Integer prt){
        this.address=addr;
        this.port=prt;
    }
    public void run(){
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
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
        while (!line.equals("NN")) 
        { 
            try
            { 
                line = input.readLine(); 
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
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    
}
