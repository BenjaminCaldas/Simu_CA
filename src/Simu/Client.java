package Simu;

/**
 * Created by lelib on 30/11/2017.
 */
public class Client {
    // Type = true telephone | Type = false mail
    private boolean Type;

    private int HeuresArrive;
    private int Attente = 0;
    private int TempsService;

    public Client(int Heure, boolean type)
    {
        HeuresArrive = Heure;
        Type = type;
        TempsService = DefinirTempsService();
    }

    public boolean getType() {
        return Type;
    }

    public void setType(boolean type) {
        Type = type;
    }

    public int getHeuresArrive() {
        return HeuresArrive;
    }

    public void setHeureArrive(int heuresArrive) {
        HeuresArrive = heuresArrive;
    }

    public int getAttente() {
        return Attente;
    }

    public void setAttente(int attente) {
        Attente = attente;
    }

    public int getTempsService() {
        return TempsService;
    }

    public void setTempsService(int tempsService) {
        TempsService = tempsService;
    }

    // Methode qui retourne le temps de service du client
    // Si c'est pour un appel telephonique le temps varie de 5 a  15 minutes â†’ [300;900] secondes
    // Si c'est pour un mail variation de 3 a  7 minutes â†’ [180;420] secondes
    private int DefinirTempsService() {
        return 0;
    }
}
