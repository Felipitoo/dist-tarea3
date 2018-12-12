/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        Cliente2 client = new Cliente2("localhost",5000,"prioridad,5001-18");
        Thread s = new Thread (client);
        s.start();

}

}
