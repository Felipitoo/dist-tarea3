/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Inception10
 */
public class Distribuidos {

    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
	public static void main(String args[])
	{
		Servidor servidor = new Servidor(5000);
                Thread t= new Thread(servidor);
                t.start();
        }


}
