package Simu;

/**
 * Created by lelib on 03/12/2017.
 */
public class Evenement {
    //0= arrivee téléphone
    //1= arrivee mail
    //2= accès téléphone
    //3= accès mail
    //4= fin traitement téléphone
    //5= fin traitement mail
    //6= Fin
    private int type; //type de l'événement

    private double time; //temps ou l'événement est lancé

    /**
     * Constructeur Evenement
     *
     * @param type
     * @param time
     */
    public Evenement(int type, double time){
        this.type=type;
        this.time=time;
    }


    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type=type;
    }

    public double getTime(){
        return time;
    }
    public void setTime(double time){
        this.time=time;
    }


    /**
     *
     * @return Transformation d'un événement en String
     */
    public String toString(){
        String typeEvent= new String();
        if (type==0){
            typeEvent="Arrivee Telephone";
        }
        else if (type==1){
            typeEvent="Arrivee Mail";
        }
        else if (type==2){
            typeEvent="Acces Telephone";
        }
        else if (type==3){
            typeEvent="Accès Mail";
        }
        else if (type==4){
            typeEvent="Fin traitement telephone";
        }
        else if (type==5){
            typeEvent="Fin traitement mail";
        }
        else if (type==6){
            typeEvent="Fin";
        }
        String print= "Time: "+ time+ ", type: "+ type+"  "+typeEvent;
        return print;
    }
}
