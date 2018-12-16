/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Inception10
 */
public class Cliente2 implements Runnable {
    private Socket socket            ;
    private DataOutputStream out     ;
    private Integer port;
    private String mensaje;
    private String address;
    private Integer origen;
    private DataOutputStream devuelve;

    public Cliente2(String addr, Integer prt,Integer ori, String men){
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
        try{
            socket = new Socket(address,origen);
            devuelve= new DataOutputStream(socket.getOutputStream());
            devuelve.writeUTF("muerto,"+Integer.toString(port)+"-algo");
            devuelve.flush();
            devuelve.close();
            socket.close();
            System.out.println("asdasdas");
            System.out.println(i);
        }
        catch(IOException l){
            System.out.println(l);
        }
        }
    }


}