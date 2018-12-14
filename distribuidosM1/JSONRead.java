import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
  public static void main(String[] args) {
    Integer i=0;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("prueba.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray blog = (JSONArray) jsonObject.get("Paramedico");

			System.out.println(blog);

			JSONArray temas = (JSONArray) jsonObject.get("Doctor");
      while(i<temas.size()){
        JSONObject doc= (JSONObject) temas.get(i);
        System.out.println("La id del dcotor es :"+doc.get("id"));
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
      System.out.println(temas);

			JSONArray inicio = (JSONArray) jsonObject.get("enfermero");
			System.out.println(inicio);


		} catch (FileNotFoundException e) {
			//manejo de error
		} catch (IOException e) {
			//manejo de error
		} catch (ParseException e) {
			//manejo de error
		}

	}

}
