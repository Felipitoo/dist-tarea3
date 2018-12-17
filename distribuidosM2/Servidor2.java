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
public class Servidor2 implements Runnable {
    private Socket          socket   ;
    private ServerSocket    server   ;
    private DataInputStream in       ;
    private Integer port;
    private String[] cont_msj;
    private Map <String,Integer> prioridades;
    private Integer[] destinos = {5000};
    private List<Integer> ded;
    private Integer i=0;
    private Integer prioridad=13;
    private Scanner sc = new Scanner(System.in);
    private String decision;
    private Integer p_enviadas=0;
    private Integer coordinacion=0;
    private String jefazo;
    private Integer iteracion=0;
    


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
        System.out.println("Desea comenzar coordinación: Y/N");
        decision = sc.nextLine();
        if(decision.equalsIgnoreCase("y")){
            System.out.println("Enviando prioridad...");
            while (i<destinos.length){
                Cliente2 client = new Cliente2("localhost",destinos[i],port,"prioridad,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
                Thread s = new Thread (client);
                s.start();
                i++;
            }
        }
        System.out.println("Prioridad enviada a todos los otros hospitales");
        while(p_enviadas<destinos.length){
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
                        if(Integer.parseInt(cont_msj[1].split("-")[1])>prioridad){
                            prioridades.put(cont_msj[1].split("-")[0],Integer.parseInt(cont_msj[1].split("-")[1]));// Se realizan Splits correspondientes para saber los datos del msj Tipo=prioridad
                        }
                        p_enviadas++;
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
        while(true){
            try
                {
                if(iteracion!=0){
                    socket = server.accept();
                    System.out.println("Client accepted");

                    // takes input from the client socket
                    in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                    String line;
                    line = in.readUTF();
                    this.cont_msj=line.split(",");
                }
                    if (coordinacion==0){
                        if(prioridades.size()==0){
                            while(i<destinos.length){
                                Cliente2 client = new Cliente2("localhost",destinos[i],port,"lider,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
                                Thread s = new Thread (client);
                                s.start();
                                i++;
                            }
                        coordinacion=1;
                        jefazo=Integer.toString(port);
                        }
                        else{
                            i=0;
                            while(i<prioridades.size() ){
                                Cliente2 client = new Cliente2("localhost",destinos[i],port,"vivo,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
                                Thread s = new Thread (client);
                                s.start();
                                i++;
                            }
                        }
                    iteracion=1;
                    }
                    if(cont_msj[0].equals("lider")){
                        System.out.println("el lider es "+ cont_msj[1].split("-")[1]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-IPoPort
                        jefazo=cont_msj[1].split("-")[1];
                    }
                    if(cont_msj[0].equals("vivo")){
                        System.out.println("el origen es "+ cont_msj[1].split("-")[0]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-IPoPort
                        Cliente2 client = new Cliente2("localhost",Integer.parseInt(cont_msj[1].split("-")[1]),port,"ok,"+"origen");
                        Thread s = new Thread (client);
                        s.start();                    
                    }
                    if(cont_msj[0].equals("muerto")){
                        coordinacion=0;
                        ded.add(Integer.parseInt(cont_msj[1].split("-")[0]));                 
                    }
                    if(cont_msj[0].equals("ok")){
                        coordinacion=1;                 
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
