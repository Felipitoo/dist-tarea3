/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;
import java.net.*;
import java.io.*;
/**
 *
 * @author Inception10
 */
public class Cliente implements Runnable {
    private Socket socket            = null; 
    private DataOutputStream out     = null;
    private Integer port;
    private String mensaje;
    private String address;
    
    public Cliente(String addr, Integer prt, String men){
        this.address=addr;
        this.port=prt;
        this.mensaje=men;
        
    }
    @Override
    public void run(){
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(this.mensaje);
            out.flush();
            out.close();
            socket.close();
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }   
    }
    
}
