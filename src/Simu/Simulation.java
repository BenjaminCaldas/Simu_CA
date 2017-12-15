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
    private boolean debut=true;

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
        AjouterEvenement(1,dateSimu+loi.getLoiExponentielleMail(dateSimu));
        if(centreAppel.getNbCourrielTraites()==0&&debut==true) {
            for(int i=0;i<centreAppel.getNc()&& i<centreAppel.getNbClientQueueCourriel(); i++){
                AjouterEvenement(3, dateSimu);
            }
            debut=false;
        }
        derniereDateSimu = dateSimu;
    }

    public void AccesMail(){
        MiseAJourDesAires();
        AjouterEvenement(5,dateSimu+loi.getDureeTraitementMail());
        centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel()+1);
        centreAppel.decrementerFileCourriel();
        derniereDateSimu = dateSimu;
    }

    public void SortieMail(){
        MiseAJourDesAires();
        centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel()-1);
        centreAppel.incrementerNbCourrielsTraites();
        if(centreAppel.getNbClientQueueTelephone()>=centreAppel.getConseillerTelephone()
                && centreAppel.getConseillerTelephone()<centreAppel.getT()){
            AjouterEvenement(2,dateSimu);
            System.out.println("Passage au téléphone !");
        }
        else if (centreAppel.getNbClientQueueCourriel()>0){
            AjouterEvenement(3,dateSimu);
            System.out.println("Reste au mail!");
        }
        derniereDateSimu=dateSimu;
    }

    public void ArriveeTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(0,dateSimu+loi.getLoiExponentionnelleTelephone(dateSimu));
        centreAppel.incrementerFileTelephone();
        if(dateSimu<1800) {
                AjouterEvenement(2, dateSimu);
        }
        derniereDateSimu = dateSimu;
    }


    public void AccesTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(4,dateSimu+loi.getDureeTraitementAppel());
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()+1);
        centreAppel.decrementerFileTelephone();
        derniereDateSimu = dateSimu;
    }

    public void SortieTelephone(){
        MiseAJourDesAires();
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()-1);
        centreAppel.incrementerNbAppelsTelephoniquesTraites();
        if(centreAppel.getNbClientQueueTelephone()>=centreAppel.getConseillerTelephone()
                && centreAppel.getConseillerTelephone()<centreAppel.getT()){
            System.out.println("Reste au téléphone !");
            AjouterEvenement(2,dateSimu);
        }
        else if (centreAppel.getNbClientQueueCourriel()>0){
            AjouterEvenement(3,dateSimu);
            System.out.println("Passage au mail !");
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
        Simulation simu = new Simulation(20,10,15);
        int i=0;
        int un=0;
        int deux=0;
        int trois=0;
        int quatre=0;
        int cinq=0;
        int six=0;

        while (i<simu.getEvenements().size()){
            simu.setDateSimu(simu.getEvenements().get(i).getTime());
            int type=simu.getEvenements().get(i).getType();
            //System.out.println(simu.getEvenements().get(i).toString());
            if (type==0){
                simu.ArriveeTelephone();
                un++;
            }
            else if (type==1){
                simu.ArriveeMail();
                deux++;
            }
            else if (type==2){
                simu.AccesTelephone();
                trois++;
            }
            else if (type==3){
                simu.AccesMail();
                quatre++;
            }
            else if (type==4){
                simu.SortieTelephone();
                cinq++;
            }
            else if (type==5){
                simu.SortieMail();
                six++;
            }
            else if (type==6){
                simu.Fin();
                break;
            }
            i++;
        }
        simu.PrintJournal();
        System.out.println("Arrivee Telephone :"+un);
        System.out.println("Arrivee mail :"+deux);
        System.out.println("Acces Telephone :"+trois);
        System.out.println("Accès mail :"+quatre);
        System.out.println("Sortie Tel :"+cinq);
        System.out.println("Sortie mail :"+six);
        System.out.println("Nb Personne Tel: "+ simu.centreAppel.getConseillerTelephone());
        System.out.println("Nb Personne Mail: "+ simu.centreAppel.getConseillerCourriel());

    }
}
