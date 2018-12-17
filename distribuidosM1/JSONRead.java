import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
  public static void main(String[] args) {
    Integer i=0 , j=0;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("medicos.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray blog = (JSONArray) jsonObject.get("Paramedico");
			i=0;
			while(i<blog.size()){
				JSONObject paramedic= (JSONObject) blog.get(i);
				System.out.println("La id del paramedico es :"+paramedic.get("id"));
				System.out.println("El nombre del doctor es:"+paramedic.get("nombre"));
				System.out.println("Su apellido es : "+paramedic.get("apellido"));
				System.out.println("Años de estudio :"+paramedic.get("estudios"));
				System.out.println("Años de experiencia :"+paramedic.get("experiencia"));
				long exp= (Long) paramedic.get("experiencia");
				long est= (Long) paramedic.get("estudios");
				long sum= exp+est;
				System.out.println(sum);
				i++;
			}

			JSONArray temas = (JSONArray) jsonObject.get("Doctor");
			i=0;
			while(i<temas.size()){
        	JSONObject doc= (JSONObject) temas.get(i);
        	System.out.println("La id del doctor es :"+doc.get("id"));
        	System.out.println("El nombre del doctor es:"+doc.get("nombre"));
        	System.out.println("Su apellido es : "+doc.get("apellido"));
        	System.out.println("Años de estudio :"+doc.get("estudios"));
        	System.out.println("Años de experiencia :"+doc.get("experiencia"));
        	long exp= (Long) doc.get("experiencia");
        	long est= (Long) doc.get("estudios");
        	long sum= exp+est;
        	System.out.println(sum);
        	i++;
      	}

			JSONArray inicio = (JSONArray) jsonObject.get("Enfermero");
			i=0;
			while(i<inicio.size()){
				JSONObject nurse= (JSONObject) inicio.get(i);
				System.out.println("La id del enfermero es :"+nurse.get("id"));
				System.out.println("El nombre del enfermero es:"+nurse.get("nombre"));
				System.out.println("Su apellido es : "+nurse.get("apellido"));
				System.out.println("Años de estudio :"+nurse.get("estudios"));
				System.out.println("Años de experiencia :"+nurse.get("experiencia"));
				long exp= (Long) nurse.get("experiencia");
				long est= (Long) nurse.get("estudios");
				long sum= exp+est;
				System.out.println(sum);
				i++;
			}

			Object obj2 = parser.parse(new FileReader("pacientes.json"));
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
				JSONObject asig= (JSONObject) ttspcs.get(0);
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
				JSONObject noreal= (JSONObject) exams.get(1);
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
				JSONObject sumin= (JSONObject) meds.get(1);
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
			JSONObject jsonObject3 = (JSONObject) obj3;
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

	}

}
