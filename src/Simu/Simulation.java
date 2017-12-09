package Simu;

import java.util.ArrayList;

/**
 * Created by lelib on 29/11/2017.
 */
public class Simulation
{
    private int dateSimu=0;
    private int derniereDateSimu=0;
    private CentreAppel centreAppel;
    private Aire aires;
    private ArrayList<Evenement> evenements;
    public Simulation()
    {
        Debut();
    }

    public void AjouterEvenement(int typeEvenement, double temps){

    }
    public void Debut()
    {
        aires = new Aire();
        centreAppel = new CentreAppel(0, 0, 0);
        evenements = new ArrayList<Evenement>();

    }

    public void Fin()
    {

    }

    public void arriveeTelephone(CentreAppel centreAppel){
        AjouterEvenement(0,centreAppel.getTempsActuel());

        if((centreAppel.getN() - centreAppel.getBt() - centreAppel.getBc() == 0) ||
                (centreAppel.getQt() > centreAppel.getBt() && centreAppel.getBt() < centreAppel.getT())){
            centreAppel.incrementerFileTelephone();
        }
        else{
            AjouterEvenement(0,centreAppel.getTempsActuel());
        }
        derniereDateSimu = dateSimu;
    }
    public void MiseAJourDesAires(){

    }
    public void accesTelephone(CentreAppel centreAppel){
        AjouterEvenement(0,centreAppel.getTempsActuel());
        centreAppel.enleverConseillerCourriels();
        MiseAJourDesAires();
        derniereDateSimu = dateSimu;
    }

}
