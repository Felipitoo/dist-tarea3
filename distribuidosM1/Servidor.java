/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;
import java.io.*;
import java.net.*;
/**
 *
 * @author Inception10
 */
public class Servidor implements Runnable {
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null;
    private Integer port;
    
    public Servidor(Integer puert){
        this.port=puert;
    }
    @Override
    public void run(){
    try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
  
            String line = ""; 
  
            // reads message from client until "Over" is sent 
            while (!line.equals("NN")) 
            { 
                try
                { 
                    line = in.readUTF(); 
                    System.out.println(line); 
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }
    }

