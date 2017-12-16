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
        this.AireFileClient = 0.0;
        this.AireFileCourriel = 0.0;
        this.AireOccupationConseiller = 0.0;
        this.AireOccupationConseillerTelephone = 0.0;
    }

    public Aire(double AireFileClient, double AireFileCourriel, double AireOccupationConseiller, double AireQbt)
    {
        this.setAireFileClient(AireFileClient);
        this.setAireFileCourriel(AireFileCourriel);
        this.setAireOccupationConseiller(AireOccupationConseiller);
        this.setAireOccupationConseillerTelephone(AireQbt);
    }

    public void MajAire(double dateSimu, double derniereDateSimu, int FileTelephone, int FileCourriel,
                        int nbConseillerTelephone, int nbConseillerCourriel, int Qbt)
    {
        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * FileTelephone));
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * FileCourriel));
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) * (nbConseillerTelephone + nbConseillerCourriel)));
        setAireOccupationConseillerTelephone(getAireOccupationConseillerTelephone() + ((dateSimu - derniereDateSimu) * Qbt));
    }

    public void MajAireFileClient(double dateSimu, double derniereDateSimu, int Qt)
    {

        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * (double)Qt));
        System.out.println(getAireFileClient());
    }

    public void MajAireFileCourriel(double dateSimu, double derniereDateSimu, int Qc)
    {
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * (double)Qc));
    }

    public void MajAireOccupationConseiller(double dateSimu, double derniereDateSimu,
                                            int nbConseillerTelephone, int nbConseillerCourriel)
    {
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) *
                (double)(nbConseillerTelephone + nbConseillerCourriel)));
    }

    public void MajAireOccupationConseillerTelephone(double dateSimu, double derniereDateSimu, int OccupationConseillerTelephone)
    {
        setAireOccupationConseillerTelephone(getAireOccupationConseillerTelephone() +
                ((dateSimu - derniereDateSimu) * (double)OccupationConseillerTelephone));
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