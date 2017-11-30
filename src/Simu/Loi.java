package Simu;

import java.util.Random;

public class Loi {

    public double getLoiExponentionnelleTelephone(int time){
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
        res= -((1/ lambda) * Math.log( 1- var));
        return res;
    }
    public double getLoiExponentielleMail(int time){
        double res=0;
        double lambda=0;
        //loi exponentielle espÃ©rance 5
        if(time>=0 && time<3600){
            lambda=1.0/30.0;
        }
        else if(time>=3600 && time<14400){
            lambda=1.0/300.00;
        }
        Random rand = new Random();
        double var=rand.nextDouble();;
        res= -((1/ lambda) * Math.log( 1- var));
        return res;
    }

    public int getNbMailNuit(){

        return loiNormale(20,80);
    }

    public int getDureeTraitementMail(){
        return loiNormale(180,420);
    }

    public int getDureeTraitementAppel(){
        return loiNormale(300,900);
    }
    public int loiNormale(int min, int max){
        int res=0;
        Random rand = new Random();
        res=min + rand.nextInt(max - min);
        return res;
    }
    public static void main(String[] args) {
        Loi loi = new Loi();
        for (int i=0; i<5; i++){
            System.out.println(loi.getLoiExponentielleMail(i*1000));
        }

    }
}

