package Simu;

import java.util.Random;

public class Loi {

    /**
     *
     * @param time L'instant où la loi est executé
     * @return La valeur de la loi
     */
    public double getLoiExponentionnelleTelephone(double time){
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

    /**
     *
     * @param time L'instant où la loi est executé
     * @return La valeur de la loi
     */
    public double getLoiExponentielleMail(double time){
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

    /**
     *
     * @return Le nombre de mail arrivés dans la nuit
     */
    public int getNbMailNuit(){

        return loiNormale(20,80);
    }

    /**
     *
     * @return La durée de traitement d'un mail
     */
    public int getDureeTraitementMail(){
        return loiNormale(180,420);
    }

    /**
     *
     * @return La durée de traitement d'un appel
     */
    public int getDureeTraitementAppel(){
        return loiNormale(300,900);
    }

    /**
     * Loi normal
     *
     * @param min Valeur minimale de la loi
     * @param max Valeur maximale de la loi
     * @return valeur de la loi
     */
    public int loiNormale(int min, int max){
        int res=0;
        Random rand = new Random();
        res=min + rand.nextInt(max - min);
        return res;
    }

    public static void main(String[] args) {
        Loi loi = new Loi();
        for (int i=0; i<14400; i++){
            System.out.println(loi.getLoiExponentielleMail(i));
        }

    }
}

