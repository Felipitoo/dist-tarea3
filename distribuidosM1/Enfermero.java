/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Inception10
 */
public class Enfermero {
    long ID;
    String name;
    String last_name;
    long est;
    long exp;
    public Enfermero(long iden,String nm,String ap,long estud,long exper){
        this.ID=iden;
        this.name=nm;
        this.last_name=ap;
        this.est=estud;
        this.exp=exper;
    }

}
