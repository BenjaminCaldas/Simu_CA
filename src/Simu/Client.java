package Simu;

/**
 * Created by lelib on 30/11/2017.
 */
public class Client {
    // Type = true telephone | Type = false mail
    private boolean type;

    private double heureArrive;
    private double attente = 0;
    private double tempsService;
    private Loi loi= new Loi();

    public Client(double heure, boolean type)
    {
        heureArrive = heure;
        this.type = type;
        if(type){
            this.tempsService = loi.getDureeTraitementAppel();
        }
        else{
            this.tempsService=loi.getDureeTraitementMail();
        }
    }
    public Client (boolean type,double heureArrive, double attente, double tempsService){
        this.type=type;
        this.heureArrive=heureArrive;
        this.attente=attente;
        this.tempsService=tempsService;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        type = type;
    }

    public double getHeuresArrive() {
        return heureArrive;
    }

    public void setHeureArrive(double heuresArrive) {
        heuresArrive = heuresArrive;
    }

    public double getAttente() {
        return attente;
    }

    public void setAttente(double attente) {
        attente = attente;
    }

    public double getTempsService() {
        return tempsService;
    }

    public void setTempsService(double tempsService) {
        tempsService = tempsService;
    }

}
