/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Inception10
 */
public class Servidor2 implements Runnable {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private Integer port;
    private String[] cont_msj;
    private Map <String,Integer> prioridades;


    public Servidor2(Integer puert){
        this.port=puert;
    }
    @Override
    public void run(){
        prioridades= new HashMap<>();
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.print(ex);
        }
        System.out.println("Server started");
        System.out.println("Waiting for a client ...");
        while(true){
            try
                {
                    socket = server.accept();
                    System.out.println("Client accepted");

                    // takes input from the client socket
                    in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                    String line;
                    line = in.readUTF();
                    this.cont_msj=line.split(",");
                    System.out.println(cont_msj[0]);
                    if(cont_msj[0].equals("prioridad")){
                        System.out.println("La prioridad es "+ cont_msj[1].split("-")[1]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-PRIORIDAD
                        prioridades.put(cont_msj[1].split("-")[0],Integer.parseInt(cont_msj[1].split("-")[1]));// Se realizan Splits correspondientes para saber los datos del msj Tipo=prioridad
                    }
                    System.out.println(prioridades.get("5001"));
                    System.out.println("Closing connection");

                    // close connection
                    socket.close();
                    in.close();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }}

    public void send_prioridad(String dest,Integer prt,int prior){
        Cliente2 client = new Cliente2(dest,prt,Integer.toString(prior));
        Thread s = new Thread (client);
        s.start();
    }

    }
