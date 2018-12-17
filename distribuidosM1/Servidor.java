/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    private List<Integer> ded;
    private Integer i=0;
    private Integer prioridad=12;
    private Scanner sc = new Scanner(System.in);
    private String decision;
    private Integer p_enviadas=0;
    private Integer coordinacion=0;
    private String jefazo;
    private Integer iteracion=0;
    private Integer j=0;
    private JSONParser parser = new JSONParser();
    ArrayList<Medico> Docs = new ArrayList<Medico>();
    ArrayList<Paramedico> Paramed = new ArrayList<Paramedico>();
    ArrayList<Enfermero> Enfer = new ArrayList<Enfermero>();




    public Servidor(Integer puert){
        this.port=puert;
    }
    @Override
    public void run(){
        try {

			Object obj = parser.parse(new FileReader("medicos.json")); // Desde aqui comienza el parseo de los JSON
                                                                        // Para cargar en memoria todos los datos
			JSONObject jsonObject = (JSONObject) obj;

			JSONArray blog = (JSONArray) jsonObject.get("Paramedico");
			i=0;
			while(i<blog.size()){
                JSONObject paramedic= (JSONObject) blog.get(i);
                long exp= (Long) paramedic.get("experiencia"); //Paramedicos
				long est= (Long) paramedic.get("estudios");
                Paramed.add(new Paramedico( (Long) paramedic.get("id"),(String) paramedic.get("nombre"),(String) paramedic.get("apellido"), est, exp));
			    i++;
			}

			JSONArray temas = (JSONArray) jsonObject.get("Doctor");
			i=0;
			while(i<temas.size()){
                JSONObject doc= (JSONObject) temas.get(i);
                long exp= (Long) doc.get("experiencia"); //Doctores
                long est= (Long) doc.get("estudios");
                Docs.add(new Medico((Long) doc.get("id"),(String) doc.get("nombre"),(String) doc.get("apellido"),est,exp));
        	    i++;
      	}

			JSONArray inicio = (JSONArray) jsonObject.get("Enfermero");
			i=0;
			while(i<inicio.size()){
				JSONObject nurse= (JSONObject) inicio.get(i);
                long exp= (Long) nurse.get("experiencia"); //Enfermeros
				long est= (Long) nurse.get("estudios");
				Enfer.add(new Enfermero((Long) nurse.get("id"),(String) nurse.get("nombre"),(String) nurse.get("apellido"),est,exp));
                i++;
			}

			Object obj2 = parser.parse(new FileReader("pacientes.json")); //Pacientes
			JSONObject jsonObject2 = (JSONObject) obj2;
			JSONArray pacientes = (JSONArray) jsonObject2.get("pacientes");
			j=0;
			while(j<pacientes.size()){
				JSONObject paciente= (JSONObject) pacientes.get(j);
				System.out.println("La id del paciente es :"+paciente.get("paciente_id"));
				JSONArray datos= (JSONArray) paciente.get("datos personales");
				JSONObject data = (JSONObject) datos.get(0);
				System.out.println("el nombre del paciente es"+data.get("nombre"));
				System.out.println("el rut del paciente es"+data.get("rut"));
				System.out.println("el edad del paciente es"+data.get("edad"));
				ArrayList sick= (ArrayList) paciente.get("enfermedades");
				i=0;
				while(i<sick.size()){
					System.out.println(sick.get(i));
					i++;
				}
				JSONArray ttspcs = (JSONArray) paciente.get("tratamientos/procedimientos");
				JSONObject asig= (JSONObject) ttspcs.get(0);  //Tratamientos
				JSONObject compl= (JSONObject) ttspcs.get(1);
				ArrayList asign2 = (ArrayList) asig.get("asignados");
				ArrayList compl2 = (ArrayList) compl.get("completados");
				i=0;
				while(i<asign2.size()){
					System.out.println(asign2.get(i));
					i++;
				}
				i=0;
				while(i<compl2.size()){
					System.out.println(compl2.get(i));
					i++;
				}
				JSONArray exams = (JSONArray) paciente.get("examenes");
				JSONObject real= (JSONObject) exams.get(0);
				JSONObject noreal= (JSONObject) exams.get(1); //examenes
				ArrayList real2 = (ArrayList) asig.get("asignados");
				ArrayList noreal2 = (ArrayList) compl.get("completados");
				i=0;
				while(i<real2.size()){
					System.out.println(real2.get(i));
					i++;
				}
				i=0;
				while(i<noreal2.size()){
					System.out.println(noreal2.get(i));
					i++;
				}
				JSONArray meds = (JSONArray) paciente.get("medicamentos");
				JSONObject recet= (JSONObject) meds.get(0);
				JSONObject sumin= (JSONObject) meds.get(1);//medicamentos
				ArrayList recet2 = (ArrayList) recet.get("recetados");
				ArrayList sumin2 = (ArrayList) sumin.get("suministrados");
				i=0;
				while(i<recet2.size()){
					System.out.println(recet2.get(i));
					i++;
				}
				i=0;
				while(i<sumin2.size()){
					System.out.println(sumin2.get(i));
					i++;
				}
			j++;
			}
			Object obj3 = parser.parse(new FileReader("requerimientos.json"));
			JSONObject jsonObject3 = (JSONObject) obj3; //requerimientos
			JSONArray requerimientos = (JSONArray) jsonObject3.get("requerimientos");
			j=0;
			while(j<requerimientos.size()){
				JSONObject requ = (JSONObject) requerimientos.get(j);
				System.out.println(requ.get("id"));
				System.out.println(requ.get("cargo"));
				JSONArray pac = (JSONArray) requ.get("pacientes");
				i=0;
				while(i<pac.size()){
					JSONObject num= (JSONObject) pac.get(i);
					Set keys = num.keySet();
					Iterator a = keys.iterator();
					while(a.hasNext()) {
						String key = (String) a.next();
						String value = (String) num.get(key);
						System.out.print("key : "+key);
						System.out.println(" value :"+value);
					}
					i++;
				}		
				j++;
			}

		} catch (FileNotFoundException e) {
			//manejo de error
		} catch (IOException e) {
			//manejo de error
		} catch (ParseException e) {
			//manejo de error
        }
        
        prioridades= new HashMap<>();
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.print(ex);
        }
        i=0;
        System.out.println("Server started");
        System.out.println("Desea comenzar coordinación: Y/N");
        decision = sc.nextLine();
        if(decision.equalsIgnoreCase("y")){
            System.out.println("Enviando prioridad...");// Flooding de mi propia prioridad a las otras maquinas
            while (i<destinos.length){
                Cliente client = new Cliente("localhost",destinos[i],port,"prioridad,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
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

                    in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                    String line;
                    line = (String) in.readUTF();
                    this.cont_msj=line.split(",");
                    System.out.println(cont_msj[0]);  //Recibo todas las prioridades de las otras maquinas
                    if(cont_msj[0].equals("prioridad")){
                        System.out.println("La prioridad es "+ cont_msj[1].split("-")[1]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-PRIORIDAD
                        if(Integer.parseInt(cont_msj[1].split("-")[1])>prioridad){ // Se almacenan solo las maquinas con prioridades mayores
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
        while(true){ // Servidor comienza a procesar peticiones
            try
                {
                if(iteracion!=0){ //Solo si es la primera iteración se salta inmediatamente a realizar la coordinación entre todos los servidores
                    socket = server.accept();
                    System.out.println("Client accepted");

                    // takes input from the client socket
                    in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                    String line;
                    line = in.readUTF();
                    this.cont_msj=line.split(",");
                }
                if (coordinacion==0 ){      // verifica si ya estan coordinados o no
                        if(prioridades.size()==0){
                            while(i<destinos.length){
                                Cliente client = new Cliente("localhost",destinos[i],port,"lider,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
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
                                Cliente client = new Cliente("localhost",destinos[i],port,"vivo,"+Integer.toString(port)+"-"+Integer.toString(prioridad));
                                Thread s = new Thread (client);
                                s.start();
                                i++;
                            }
                        }

                    iteracion=1;
                    }
                    if(cont_msj[0].equals("lider")){ // Se procesa si son lideres
                        System.out.println("el lider es "+ cont_msj[1].split("-")[1]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-IPoPort
                        jefazo=cont_msj[1].split("-")[1];
                    }
                    if(cont_msj[0].equals("vivo")){ // se  pregunta si esta vivo
                        System.out.println("el origen es "+ cont_msj[1].split("-")[0]); //Se asume que el mensaje de prioridad es de la forma TIPO;ORIGEN-IPoPort
                        Cliente client = new Cliente("localhost",Integer.parseInt(cont_msj[1].split("-")[1]),port,"ok,"+"origen");
                        Thread s = new Thread (client);
                        s.start();                    
                    }
                    if(cont_msj[0].equals("muerto")){ // quiere decir que envio mensaje a un server muerto
                        coordinacion=0;                 //por lo tanto se debe coordinar denuevo
                        ded.add(Integer.parseInt(cont_msj[1].split("-")[0]));                 
                    }
                    if(cont_msj[0].equals("ok")){ // verifica que un server esta vivo
                        coordinacion=1;                 
                    }



                    System.out.println("Closing connection");

                    // close connection
                    socket.close();
                    in.close();
                    System.out.println(jefazo);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }}


    }
