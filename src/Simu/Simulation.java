package Simu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lelib on 29/11/2017.
 */
public class Simulation
{
    private double dateSimu=0;
    private double derniereDateSimu=0;
    private CentreAppel centreAppel;
    private Aire aires;
    private boolean debut=true;

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
    
    public CentreAppel getCentreAppel() {return centreAppel;}

    /**
     * Setter de la date de simulation
     * @param dateSimu
     */
    public void setDateSimu(double dateSimu) {
        this.dateSimu = dateSimu;
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

    private ArrayList<Evenement> evenements; //liste des événements
    private Loi loi = new Loi(); //classe servant à utiliser les lois
    private int nbMailsNuit = 0; //nb de mails arrivés durant la nuit
    
    private int getNbMailsNuit() { return nbMailsNuit; }

    private double tempsAttenteClientTel=0.0; //Temps d'attente client
    private double delaiReponseCourrier=0.0; //Delai réponse courrier
    private double tauxOccupationConseiller=0.0; //Taux d'occupation des conseillers
    private double tauxOccupationPosteTel=0.0; //Taux d'occupation des conseillers au poste téléphonique
    private double nbClientMoyenQueueTelephone=0.0; //Nombre de client moyen dans la queue téléphonique
    private double nbClientMoyenQueueCourrier =0.0; //Nombre de client moyen dans la queue des mails

    /**
     * Constructeur de la Simulation
     *
     * @param nbConseillers Le nombre total de conseillers
     * @param nbConseillersTel Le nombre de conseillers affectés au téléphone au départ
     * @param nbPostesTel Nombre de postes téléphoniques disponibles
     */
    public Simulation(int nbConseillers, int nbConseillersTel, int nbPostesTel)
    {
        evenements=new ArrayList<Evenement>();
        centreAppel=new CentreAppel(nbConseillers,nbConseillersTel,nbPostesTel);
        aires = new Aire();
        Debut();
    }

    /**
     * Evénement de début
     */
    public void Debut()
    {
        dateSimu=0;
        nbMailsNuit = loi.getNbMailNuit();
        centreAppel.setNbClientQueueCourriel(nbMailsNuit);
        AjouterEvenement(6,centreAppel.getFermeture());
        AjouterEvenement(0,loi.getLoiExponentionnelleTelephone(dateSimu));
        AjouterEvenement(1,loi.getLoiExponentielleMail(dateSimu));
        derniereDateSimu=dateSimu;
    }

    /**
     * Ajout d'un Evenement dans la liste d'evenements
     * @param typeEvenement
     * @param temps
     * @return
     */
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

    /**
     * Affichage du journal d'événement
     */
    public void PrintJournal(){
        Iterator<Evenement> it = evenements.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    /**
     * Evenement de fin
     */
    public void Fin()
    {
        MiseAJourDesAires();

        //Calcul des indicateurs statistiques
        tempsAttenteClientTel=aires.getAireFileClient()/centreAppel.getNbAppelTraites();
        if (centreAppel.getNbCourrielTraites() != 0) {
            delaiReponseCourrier=aires.getAireFileCourriel()/centreAppel.getNbCourrielTraites();
        } else {
            delaiReponseCourrier = 1000000; // Si pas de courriers traités, on mets un délai anormalement long (pour déterminer le score)
        }
        tauxOccupationConseiller=aires.getAireOccupationConseiller()/(centreAppel.getFermeture()*centreAppel.getN());
        tauxOccupationPosteTel=aires.getAireOccupationConseillerTelephone()/(centreAppel.getFermeture()*centreAppel.getT());
        nbClientMoyenQueueTelephone=aires.getAireFileClient()/centreAppel.getFermeture();
        nbClientMoyenQueueCourrier=aires.getAireFileCourriel()/centreAppel.getFermeture();

    }

    /**
     * Evenement Arrivée d'un Mail
     */
    public void ArriveeMail(){
        MiseAJourDesAires();
        centreAppel.setNbClientQueueCourriel(centreAppel.getNbClientQueueCourriel()+1);
        AjouterEvenement(1,dateSimu+loi.getLoiExponentielleMail(dateSimu));
        //Si l'on est au début de la simulation on décrémente le nombre de personnes affectés au mail
        //et on ajoute un evenement d'accès mail jusqu'à ce qu'il n'y ai plus de personnes affectés au mail
        //ou de mail en attente
        if(centreAppel.getNbCourrielTraites()==0&&debut==true) {
            for (int i = 0; i < centreAppel.getNc(); i++){
                if (i < centreAppel.getNbClientQueueCourriel()) {
                    AjouterEvenement(3, dateSimu);

                }
                centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel() - 1);
            }
            debut=false;
        }
        else if(centreAppel.getConseillerTelephone()+centreAppel.getConseillerCourriel()<centreAppel.getN()&&centreAppel.getNbCourrielTraites()>0) {
            AjouterEvenement(3, dateSimu);
        }
        derniereDateSimu = dateSimu;
    }

    /**
     * Evenement Accès à un mail
     */
    public void AccesMail(){
        MiseAJourDesAires();
        AjouterEvenement(5,dateSimu+loi.getDureeTraitementMail());
        centreAppel.setConseillerCourriel(centreAppel.getConseillerCourriel()+1);
        centreAppel.decrementerFileCourriel();
        derniereDateSimu = dateSimu;
    }

    /**
     * Evenement de sortie d'un mail
     */
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

    /**
     * Evenement d'arrivée d'un appel téléphonique
     */
    public void ArriveeTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(0,dateSimu+loi.getLoiExponentionnelleTelephone(dateSimu));
        centreAppel.incrementerFileTelephone();
        if(centreAppel.getConseillerTelephone()<centreAppel.getT()
                && centreAppel.getConseillerTelephone()+centreAppel.getConseillerCourriel()<centreAppel.getN()) {
                AjouterEvenement(2, dateSimu);
        }
        derniereDateSimu = dateSimu;
    }

    /**
     * Evenement d'accès à un appel téléphonique
     */
    public void AccesTelephone(){
        MiseAJourDesAires();
        AjouterEvenement(4,dateSimu+loi.getDureeTraitementAppel());
        centreAppel.setConseillerTelephone(centreAppel.getConseillerTelephone()+1);
        centreAppel.decrementerFileTelephone();
        derniereDateSimu = dateSimu;
    }

    /**
     * Evenement de sortie d'un appel téléphonique
     */
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

    /**
     * Mise à jour des différentes aires
     */
    public void MiseAJourDesAires(){

        aires.MajAireFileClient(dateSimu, derniereDateSimu,centreAppel.getNbClientQueueTelephone());
        aires.MajAireFileCourriel(dateSimu, derniereDateSimu,centreAppel.getNbClientQueueCourriel());
        aires.MajAireOccupationConseiller(dateSimu,derniereDateSimu,centreAppel.getConseillerTelephone(),centreAppel.getConseillerCourriel());
        aires.MajAireOccupationConseillerTelephone(dateSimu, derniereDateSimu,centreAppel.getConseillerTelephone());

    }

    /**
     * Fonction Main
     * @param args
     */
    public static void main(String[] args) {
        //Nouvelle simulation
        Simulation simu = new Simulation(10,18,12);
        int i=0;
        int un=0;
        int deux=0;
        int trois=0;
        int quatre=0;
        int cinq=0;
        int six=0;
        //Pour chaque élément de la liste d'événement
        while (i<simu.getEvenements().size()){
            simu.setDateSimu(simu.getEvenements().get(i).getTime());

            int type=simu.getEvenements().get(i).getType();

            //Si c'est un événement d'arrivée d'un appel téléphonique
            if (type==0){
                simu.ArriveeTelephone();
                un++;
            }
            else //Si c'est un événement d'arrivée d'un mail
                if (type==1){
                simu.ArriveeMail();
                deux++;
            }
            else //Si c'est un événement d'accès à un appel téléphonique
                if (type==2){
                simu.AccesTelephone();
                trois++;
            }
            else //Si c'est un événement d'accès à un mail
                if (type==3){
                simu.AccesMail();
                quatre++;
            }
            else //Si c'est un événement de sortie d'appel téléphonique
                if (type==4){
                simu.SortieTelephone();
                cinq++;
            }
            else //Si c'est un événement de sortie de mail
                if (type==5){
                simu.SortieMail();
                six++;
            }
            else //Si c'est un événement de fin
                if (type==6){
                simu.Fin();
                break;
            }
            /*System.out.println("Nb conseiller inoccuppe: "+ (simu.centreAppel.getN()-simu.centreAppel.getConseillerCourriel()
                    -simu.centreAppel.getConseillerTelephone()));
            System.out.println("Nb conseiller tel: "+ (simu.centreAppel.getConseillerTelephone()));
            System.out.println("Nb conseiller mail: "+ (simu.centreAppel.getConseillerCourriel()));
            System.out.println(simu.getEvenements().get(i).toString());
            System.out.println(" ");*/
            i++;
        }
        //simu.PrintJournal();
        //Rappel des ressources (car modification intelligente en cas d'erreur de saisie
        System.out.println("Nb employés : " + simu.getCentreAppel().getN() + ", Nb employés tel : " + simu.getCentreAppel().getNt() + " (" +simu.getCentreAppel().getT() +  " postes tel), Nb employés courriel : " + simu.getCentreAppel().getNc());
        System.out.println("Nb mails arrivés dans la nuit :" + simu.getNbMailsNuit());
        System.out.println("Arrivee mail (hors nuit) :"+deux);
        System.out.println("Arrivee Telephone :"+un);
        System.out.println("Acces Telephone :"+trois);
        System.out.println("Accès mail :"+quatre);
        System.out.println("Sortie Tel :"+cinq);
        System.out.println("Sortie mail :"+six);
        System.out.println("Nb Personne Tel: "+ simu.centreAppel.getConseillerTelephone());
        System.out.println("Nb Personne Mail: "+ simu.centreAppel.getConseillerCourriel());
        System.out.println("Appel en attente: "+ simu.centreAppel.getNbClientQueueTelephone());
        System.out.println("Mail en attente: "+ simu.centreAppel.getNbClientQueueCourriel());
        System.out.println("Temps d'attente moyen client téléphone : "+ simu.tempsAttenteClientTel);
        System.out.println("Délai réponse courrier : "+ simu.delaiReponseCourrier);
        System.out.println("Taux d'occupation conseiller : "+ simu.tauxOccupationConseiller);
        System.out.println("Taux d'occupation poste téléphonique : "+ simu.tauxOccupationPosteTel);
        System.out.println("Nb moyen de clients dans la queue du telephone: "+ simu.nbClientMoyenQueueTelephone);
        System.out.println("Nb moyen de clients dans la queue des mails: "+ simu.nbClientMoyenQueueCourrier);
    }
}
