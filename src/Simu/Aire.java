package Simu;

/**
 * Created by lelib on 29/11/2017.
 */
public class Aire
{
    private double AireFileClient; //File attente client téléphone
    private double AireFileCourriel; //File attente mail
    private double AireOccupationConseiller; //
    private double AireOccupationConseillerTelephone;

    public Aire()
    {
        this.AireFileClient = 0;
        this.AireFileCourriel = 0;
        this.AireOccupationConseiller = 0;
        this.AireOccupationConseillerTelephone = 0;
    }

    public Aire(double AireFileClient, double AireFileCourriel, double AireOccupationConseiller, double AireQbt)
    {
        this.setAireFileClient(AireFileClient);
        this.setAireFileCourriel(AireFileCourriel);
        this.setAireOccupationConseiller(AireOccupationConseiller);
        this.setAireOccupationConseillerTelephone(AireQbt);
    }

    public void MajAire(int dateSimu, int derniereDateSimu, int FileTelephone, int FileCourriel,
                        int nbConseillerTelephone, int nbConseillerCourriel, int Qbt)
    {
        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * FileTelephone));
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * FileCourriel));
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) * (nbConseillerTelephone + nbConseillerCourriel)));
        setAireOccupationConseillerTelephone(getAireOccupationConseillerTelephone() + ((dateSimu - derniereDateSimu) * Qbt));
    }

    public void MajAireFileClient(int dateSimu, int derniereDateSimu, int Qt)
    {
        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * Qt));
    }

    public void MajAireFileCourriel(int dateSimu, int derniereDateSimu, int Qc)
    {
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * Qc));
    }

    public void MajAireOccupationConseiller(int dateSimu,
                                            int derniereDateSimu, int nbConseillerTelephone, int nbConseillerCourriel)
    {
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) *
                (nbConseillerTelephone + nbConseillerCourriel)));
    }

    public void MajAireOccupationConseillerTelephone(int dateSimu, int derniereDateSimu, int OccupationConseillerTelephone)
    {
        setAireOccupationConseillerTelephone(getAireOccupationConseillerTelephone() +
                ((dateSimu - derniereDateSimu) * OccupationConseillerTelephone));
    }


    public double getAireFileClient() {
        return AireFileClient;
    }

    public void setAireFileClient(double aireQt) {
        AireFileClient = aireQt;
    }

    public double getAireFileCourriel() {
        return AireFileCourriel;
    }

    public void setAireFileCourriel(double aireFileCourriel) {
        AireFileCourriel = aireFileCourriel;
    }

    public double getAireOccupationConseiller() {
        return AireOccupationConseiller;
    }

    public void setAireOccupationConseiller(double aireQb) {
        AireOccupationConseiller = aireQb;
    }

    public double getAireOccupationConseillerTelephone() {
        return AireOccupationConseillerTelephone;
    }

    public void setAireOccupationConseillerTelephone(double aireQbt) {
        AireOccupationConseillerTelephone = aireQbt;
    }
}