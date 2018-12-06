/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos2;

/**
 *
 * @author Inception10
 */
public class Distribuidos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente client = new Cliente("192.168.0.18",5000);
        //Cliente client2 = new Cliente("192.168.0.18",5000);
        Thread s = new Thread (client);
        s.start();
    }
    
}
