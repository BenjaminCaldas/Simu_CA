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
    private Loi loi = new Loi();

    public Simulation(int nbConseillers, int nbConseillersTel, int nbPostesTel)
    {
        evenements=new ArrayList<Evenement>();
        centreAppel=new CentreAppel(nbConseillers,nbConseillersTel,nbPostesTel);
        aires = new Aire();
        Debut();
    }

    public void Debut()
    {
        dateSimu=0;
        centreAppel.setNbClientQueueCourriel(loi.getNbMailNuit());
        AjouterEvenement(0,loi.getLoiExponentionnelleTelephone(dateSimu));
        AjouterEvenement(1,loi.getLoiExponentielleMail(dateSimu));
        AjouterEvenement(6,centreAppel.getFermeture());
        derniereDateSimu=dateSimu;
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

    public void Fin()
    {
        MiseAJourDesAires();
    }

    public void ArriveeMail(){
        MiseAJourDesAires();
        centreAppel.setNbClientQueueCourriel(centreAppel.getNbClientQueueCourriel()+1);
        AjouterEvenement(1,centreAppel.getTempsActuel()+loi.getLoiExponentielleMail(dateSimu));
        derniereDateSimu=dateSimu;

    }

    public void AccesMail(){
        MiseAJourDesAires();
        AjouterEvenement(5,centreAppel.getTempsActuel()+loi.getDureeTraitementMail());
        centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel()+1);
        centreAppel.decrementerFileCourriel();
        derniereDateSimu = dateSimu;
    }

    public void SortieMail(){
        MiseAJourDesAires();
        centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel()-1);
        centreAppel.incrementerNbCourrielsTraites();
        if(centreAppel.getNbClientQueueTelephone()>centreAppel.getConseillerTelephone() && centreAppel.getConseillerTelephone()<centreAppel.getT()){
            AjouterEvenement(2,dateSimu);
        }
        else if (centreAppel.getNbClientQueueCourriel()>0){
            AjouterEvenement(3,dateSimu);
        }
        derniereDateSimu=dateSimu;
    }

    public void ArriveeTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(0,centreAppel.getTempsActuel()+loi.getLoiExponentionnelleTelephone(dateSimu));
        int nbConseillerInoccuppe=centreAppel.getN() - centreAppel.getConseillerTelephone() - centreAppel.getConseillerCourriel();
        if(( nbConseillerInoccuppe== 0) || (centreAppel.getNbClientQueueTelephone() > centreAppel.getConseillerTelephone() &&
                        centreAppel.getConseillerTelephone() < centreAppel.getT())){
            centreAppel.incrementerFileTelephone();
        }
        else{
            AjouterEvenement(0,centreAppel.getTempsActuel());
        }
        derniereDateSimu = dateSimu;

        MiseAJourDesAires();
        centreAppel.setNbClientQueueCourriel(centreAppel.getNbClientQueueCourriel()+1);
        AjouterEvenement(1,centreAppel.getTempsActuel()+loi.getLoiExponentielleMail(dateSimu));
        derniereDateSimu=dateSimu;
    }


    public void AccesTelephone(CentreAppel centreAppel){
        AjouterEvenement(0,centreAppel.getTempsActuel());
        //centreAppel.enleverConseillerCourriels();
        MiseAJourDesAires();
        derniereDateSimu = dateSimu;
    }

    public void SortieTelephone(){
        MiseAJourDesAires();
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()-1);
        centreAppel.setNbAppelTraites(centreAppel.getNbAppelTraites()+1);
        if(centreAppel.getNbClientQueueTelephone()>0){
            AjouterEvenement(2,dateSimu);
        }
        else if (centreAppel.getNbClientQueueCourriel()>0){
            AjouterEvenement(3,dateSimu);
        }
        derniereDateSimu=dateSimu;
    }

    public void MiseAJourDesAires(){

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
