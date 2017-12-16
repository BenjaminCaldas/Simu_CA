package Simu;

/**
 * Created by lelib on 29/11/2017.
 */
public class Aire
{
    private double AireFileClient; //File attente client téléphone
    private double AireFileCourriel; //File attente mail
    private double AireOccupationConseiller; //Aire de l'occupation globale des conseillers
    private double AireOccupationConseillerTelephone; //Aire de l'occupation des conseillers au téléphone

    /**
     * Constructeur des Aires
     */
    public Aire()
    {
        this.AireFileClient = 0.0;
        this.AireFileCourriel = 0.0;
        this.AireOccupationConseiller = 0.0;
        this.AireOccupationConseillerTelephone = 0.0;
    }

    /**
     * Constructeur des aires
     *
     * @param AireFileClient
     * @param AireFileCourriel
     * @param AireOccupationConseiller
     * @param AireQbt
     */
    public Aire(double AireFileClient, double AireFileCourriel, double AireOccupationConseiller, double AireQbt)
    {
        this.setAireFileClient(AireFileClient);
        this.setAireFileCourriel(AireFileCourriel);
        this.setAireOccupationConseiller(AireOccupationConseiller);
        this.setAireOccupationConseillerTelephone(AireQbt);
    }

    /**
     * Mise à jour des aires
     *
     * @param dateSimu
     * @param derniereDateSimu
     * @param FileTelephone
     * @param FileCourriel
     * @param nbConseillerTelephone
     * @param nbConseillerCourriel
     * @param Qbt
     */
    public void MajAire(double dateSimu, double derniereDateSimu, int FileTelephone, int FileCourriel,
                        int nbConseillerTelephone, int nbConseillerCourriel, int Qbt)
    {
        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * FileTelephone));
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * FileCourriel));
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) * (nbConseillerTelephone + nbConseillerCourriel)));
        setAireOccupationConseillerTelephone(getAireOccupationConseillerTelephone() + ((dateSimu - derniereDateSimu) * Qbt));
    }

    /**
     * Mise à jour de l'aire de la taille de la file d'attente client téléphone
     *
     * @param dateSimu
     * @param derniereDateSimu
     * @param Qt
     */
    public void MajAireFileClient(double dateSimu, double derniereDateSimu, int Qt)
    {

        setAireFileClient(getAireFileClient() + ((dateSimu - derniereDateSimu) * (double)Qt));
        System.out.println(getAireFileClient());
    }

    /**
     * Mise à jour de l'aire de la taille de la file d'attente client courriel
     *
     * @param dateSimu
     * @param derniereDateSimu
     * @param Qc
     */
    public void MajAireFileCourriel(double dateSimu, double derniereDateSimu, int Qc)
    {
        setAireFileCourriel(getAireFileCourriel() + ((dateSimu - derniereDateSimu) * (double)Qc));
    }

    /**
     * Mise à jour de l'aire de l'occupation globale des conseillers
     *
     * @param dateSimu
     * @param derniereDateSimu
     * @param nbConseillerTelephone
     * @param nbConseillerCourriel
     */
    public void MajAireOccupationConseiller(double dateSimu, double derniereDateSimu,
                                            int nbConseillerTelephone, int nbConseillerCourriel)
    {
        setAireOccupationConseiller(getAireOccupationConseiller() + ((dateSimu - derniereDateSimu) *
                (double)(nbConseillerTelephone + nbConseillerCourriel)));
    }

    /**
     * Mise à jour de l'aire de l'occupation des conseillers au téléphone
     *
     * @param dateSimu
     * @param derniereDateSimu
     * @param OccupationConseillerTelephone
     */
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