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
 * @author Felipito
 */
public class Servidor implements Runnable {
    private Socket          socket   ;
    private ServerSocket    server   ;
    private DataInputStream in       ;
    private Integer port;
    private String[] cont_msj;
    private Map <String,Integer> prioridades;
    private Integer[] destinos = {5001};
    private Integer i=0;
    private Integer prioridad=15;
    private Scanner sc = new Scanner(System.in);
    private String decision;
    private Cliente client;
    


    public Servidor(Integer puert){
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
        System.out.println("Desea comenzar coordinación: Y/N");
        decision = sc.nextLine();
        if(decision.equalsIgnoreCase("y")){
            System.out.println("Enviando prioridad...");
            while (i<destinos.length){
                Cliente client1 = new Cliente("localhost",destinos[i],port,"prioridad,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
                client1.start();
                i++;
            }
        }
        System.out.println("Prioridad enviada a todos los otros hospitales");

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


    }
