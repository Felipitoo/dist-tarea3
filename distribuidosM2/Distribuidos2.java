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
        Servidor2 servidor = new Servidor2(5001);
        Thread t= new Thread(servidor);
        t.start();

}

}
