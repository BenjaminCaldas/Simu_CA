package Simu;

import java.util.Random;

public class Loi {

    public double getLoiPoissonTelephone(int time){
        double res=0;
        double lambda=0;
        //loi exponentielle espÃ©rance 5
        if(time>=0 && time<3600){
            lambda=1.0/300.0;
        }
        else if(time>=3600 && time<10800){
            lambda=1.0/60.0;
        }
        else if(time>=10800 && time<14400){
            lambda=1.0/600.0;
        }
        Random rand = new Random();
        double var=rand.nextDouble();
        System.out.println("Lambda: "+lambda);
        System.out.println("var: "+var);
        res= -((1/ lambda) * Math.log( 1- var));
        return res;
    }
    public static void main(String[] args) {
        Loi loi = new Loi();
        for (int i=0; i<5; i++){
            System.out.println(loi.getLoiPoissonTelephone(0+i*100));
        }
        for (int i=0; i<5; i++){
            System.out.println(loi.getLoiPoissonTelephone(3600+i*100));
        }
        for (int i=0; i<5; i++){
            System.out.println("i:"+ loi.getLoiPoissonTelephone(10800+i*100));
        }
    }
}

