
// Timestamp para saber tiempo de modificacion

import java.util.Date;
import java.sql.Timestamp;

public class TimeStamp
{
    public static void main( String[] args )
    {

 Date date= new Date();

 long time = date.getTime();
     System.out.println("Tiempo: " + time);

 Timestamp ts = new Timestamp(time);
 System.out.println("Timestamp actual: " + ts);
    }
}
