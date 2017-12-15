package Simu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lelib on 29/11/2017.
 */
public class Simulation
{
    public void setDateSimu(double dateSimu) {
        this.dateSimu = dateSimu;
    }

    private double dateSimu=0;
    private double derniereDateSimu=0;
    private CentreAppel centreAppel;
    private Aire aires;

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }

    public double getTempsAttenteClientTel() {
        return tempsAttenteClientTel;
    }

    public double getDelaiReponseCourrier() {
        return delaiReponseCourrier;
    }

    public double getTauxOccupationConseiller() {
        return tauxOccupationConseiller;
    }

    public double getTauxOccupationPosteTel() {
        return tauxOccupationPosteTel;
    }

    private ArrayList<Evenement> evenements;
    private Loi loi = new Loi();

    private double tempsAttenteClientTel=0.0;
    private double delaiReponseCourrier=0.0;
    private double tauxOccupationConseiller=0.0;
    private double tauxOccupationPosteTel=0.0;

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
        tempsAttenteClientTel=aires.getAireFileClient()/centreAppel.getNbAppelTraites();
        delaiReponseCourrier=aires.getAireFileCourriel()/centreAppel.getNbCourrielTraites();
        tauxOccupationConseiller=aires.getAireOccupationConseiller()/centreAppel.getFermeture();
        tauxOccupationPosteTel=aires.getAireOccupationConseillerTelephone()/centreAppel.getFermeture();
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
        centreAppel.incrementerFileTelephone();
        derniereDateSimu = dateSimu;
    }


    public void AccesTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(4,centreAppel.getTempsActuel()+loi.getDureeTraitementAppel());
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()+1);
        centreAppel.decrementerFileTelephone();
        derniereDateSimu = dateSimu;
    }

    public void SortieTelephone(){
        MiseAJourDesAires();
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()-1);
        centreAppel.incrementerNbAppelsTelephoniquesTraites();
        if(centreAppel.getNbClientQueueTelephone()>centreAppel.getConseillerTelephone()
                && centreAppel.getConseillerTelephone()<centreAppel.getT()){
            AjouterEvenement(2,dateSimu);
        }
        else if (centreAppel.getNbClientQueueCourriel()>0){
            AjouterEvenement(3,dateSimu);
        }
        derniereDateSimu=dateSimu;
    }

    public void MiseAJourDesAires(){

        aires.MajAireFileClient(dateSimu, derniereDateSimu,centreAppel.getNbClientQueueTelephone());
        aires.MajAireFileCourriel(dateSimu, derniereDateSimu,centreAppel.getNbClientQueueCourriel());
        aires.MajAireOccupationConseiller(dateSimu,derniereDateSimu,centreAppel.getConseillerTelephone(),centreAppel.getConseillerCourriel());
        aires.MajAireOccupationConseillerTelephone(dateSimu, derniereDateSimu,centreAppel.getConseillerTelephone());

    }

    public static void main(String[] args) {
        Simulation simu = new Simulation(10,5,6);
        int i=0;
        while (i<simu.getEvenements().size()){
            simu.setDateSimu(simu.getEvenements().get(i).getTime());
            int type=simu.getEvenements().get(i).getType();
            System.out.println(simu.getEvenements().get(i).toString());
            if (type==0){
                simu.ArriveeTelephone();
            }
            else if (type==1){
                simu.ArriveeMail();
            }
            else if (type==2){
                simu.AccesTelephone();
            }
            else if (type==3){
                simu.AccesTelephone();
            }
            else if (type==4){
                simu.SortieTelephone();
            }
            else if (type==5){
                simu.SortieMail();
            }
            else if (type==6){
                simu.Fin();
            }
            i++;
        }
    }
}
