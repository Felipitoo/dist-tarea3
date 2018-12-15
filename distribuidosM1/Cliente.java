/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public Integer origen;

    public Cliente(String addr, Integer prt,Integer ori, String men){
        this.address=addr;
        this.port=prt;
        this.mensaje=men;
        this.origen=ori;

    }
    @Override
    public void run(){
        try
        {   System.out.println("Connected");
            socket = new Socket(address, port);
            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(this.mensaje);
            out.flush();
            out.close();
            socket.close();
        }
        catch(UnknownHostException u)
        {
            System.out.println("fallo la conexion");
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

}
