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
    private int type;

    private double time;
    private Client client;

    public Evenement(int type, double time){
        this.type=type;
        this.time=time;
    }

    public Evenement(int type, double time, Client client){
        this.type=type;
        this.time=time;
        this.client=client;
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

    public Client getClient(){
        return client;
    }

    public String toString(){
        String print= "Time: "+ time+ ", type: "+ type;
        return print;
    }
}
