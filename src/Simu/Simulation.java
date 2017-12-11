package Simu;

import java.util.ArrayList;
import java.util.Iterator;

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
    public Simulation(int nbConseillers, int nbConseillersTel, int nbPostesTel)
    {
        Debut();
        evenements=new ArrayList<Evenement>();
        centreAppel=new CentreAppel(nbConseillers,nbConseillersTel,nbPostesTel);
    }

    public int AjouterEvenement(int typeEvenement, double temps){
        Iterator<Evenement> it = evenements.iterator();
        int i=0;
        while (it.hasNext()) {
            if (it.next().getTime() > temps){
                evenements.add(i,new Evenement(typeEvenement,temps));
                return 0;
            }
            i++;
        }
        evenements.add(new Evenement(typeEvenement,temps));
        return 0;
    }
    public void PrintJournal(){
        Iterator<Evenement> it = evenements.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
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

    public void ArriveeTelephone(CentreAppel centreAppel){
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
    public void AccesTelephone(CentreAppel centreAppel){
        AjouterEvenement(0,centreAppel.getTempsActuel());
        centreAppel.enleverConseillerCourriels();
        MiseAJourDesAires();
        derniereDateSimu = dateSimu;
    }

    public void SortieMail(){
        MiseAJourDesAires();
        centreAppel.setBc(centreAppel.getBc()-1);
        centreAppel.setNbc(centreAppel.getNc()+1);
        if(centreAppel.getQt()>centreAppel.getBt() && centreAppel.getBt()<centreAppel.getT()){
            AjouterEvenement(2,dateSimu);
        }
        else if (centreAppel.getQc()>0){
            AjouterEvenement(3,dateSimu);
        }
        derniereDateSimu=dateSimu;
    }

    public static void main(String[] args) {
        Simulation simu = new Simulation(10,5,6);
        simu.AjouterEvenement(3,45);
        simu.AjouterEvenement(1,435);
        simu.AjouterEvenement(4,4);
        simu.AjouterEvenement(2,16);
        simu.AjouterEvenement(5,3);
        simu.AjouterEvenement(1,8);
        simu.AjouterEvenement(5,415);
        simu.AjouterEvenement(4,4523);
        simu.AjouterEvenement(1,1);
        simu.AjouterEvenement(2,2452);
        simu.AjouterEvenement(3,451);
        simu.AjouterEvenement(4,415);

        simu.PrintJournal();
    }
}
