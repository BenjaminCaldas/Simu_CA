/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Simu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Benjamin
 */
public class simuExcel {
    public static void main(String[] args) {
        // Initialisation du excel
        // Mettre le .jar sur git
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Simu_CA");
        int nb_simus = 0;
        double worst_CNT = 0;
        double worst_TAA = 0;
        double worst_DRC = 0;
        // Ligne attributs
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Simulations");
        row0.createCell(1).setCellValue("N");
        row0.createCell(2).setCellValue("Nt");
        row0.createCell(3).setCellValue("Nc");
        row0.createCell(4).setCellValue("T");
        row0.createCell(5).setCellValue("CNT");
        row0.createCell(6).setCellValue("TAA");
        row0.createCell(7).setCellValue("DRC");
        row0.createCell(8).setCellValue("TOC");
        row0.createCell(9).setCellValue("TOP");
        row0.createCell(10).setCellValue("Score (%)");
        
        // Réalisation des simulations
        for (int N = 4; N <= 30; ++N) {
            for (int Nt = 0; Nt <= N; ++Nt) {
                for (int T = 1; T <= Nt; ++T) {
                    Simulation simu = new Simulation(N,Nt,T);
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
                    /*System.out.println("Nb employés : " + simu.getCentreAppel().getN() + ", Nb employés tel : " + simu.getCentreAppel().getNt() + " (" +simu.getCentreAppel().getT() +  " postes tel), Nb employés courriel : " + simu.getCentreAppel().getNc());
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
                    System.out.println("Nb moyen de clients dans la queue des mails: "+ simu.nbClientMoyenQueueCourrier);*/


                    if (simu.getCentreAppel().getNbClientQueueCourriel() > worst_CNT) {
                        worst_CNT = simu.getCentreAppel().getNbClientQueueCourriel();
                    }
                    if (simu.getTempsAttenteClientTel() > worst_TAA) {
                        worst_TAA = simu.getTempsAttenteClientTel();
                    }
                    if (simu.getDelaiReponseCourrier() > worst_DRC) {
                        worst_DRC = simu.getDelaiReponseCourrier();
                    }

                    System.out.println("Nb employés : " + simu.getCentreAppel().getN() + ", Nb employés tel : " + simu.getCentreAppel().getNt() + " (" +simu.getCentreAppel().getT() +  " postes tel), Nb employés courriel : " + simu.getCentreAppel().getNc());
                    System.out.println("Mail en attente: "+ simu.getCentreAppel().getNbClientQueueCourriel());
                    System.out.println("Temps d'attente moyen client téléphone : "+ simu.getTempsAttenteClientTel());
                    System.out.println("Délai réponse courrier : "+ simu.getDelaiReponseCourrier());
                    System.out.println("Taux d'occupation conseiller : "+ simu.getTauxOccupationConseiller());
                    System.out.println("Taux d'occupation poste téléphonique : "+ simu.getTauxOccupationPosteTel());
                    double score = (20 - (simu.getCentreAppel().getNbClientQueueCourriel() / (worst_CNT/20))) +
                            (20 - (simu.getTempsAttenteClientTel() / (worst_TAA/20))) +
                            (20 - (simu.getDelaiReponseCourrier() / (worst_DRC/20))) +
                            20 * simu.getTauxOccupationConseiller() + 
                            20 * simu.getTauxOccupationPosteTel();
                    System.out.println("Décision : " + score);

                    // Lignes résultats
                    ++nb_simus;
                    HSSFRow row1 = sheet.createRow(nb_simus);
                    row1.createCell(0).setCellValue("Simu " + nb_simus);
                    row1.createCell(1).setCellValue(simu.getCentreAppel().getN());
                    row1.createCell(2).setCellValue(simu.getCentreAppel().getNt());
                    row1.createCell(3).setCellValue(simu.getCentreAppel().getNc());
                    row1.createCell(4).setCellValue(simu.getCentreAppel().getT());
                    row1.createCell(5).setCellValue(simu.getCentreAppel().getNbClientQueueCourriel());
                    row1.createCell(6).setCellValue(simu.getTempsAttenteClientTel());
                    row1.createCell(7).setCellValue(simu.getDelaiReponseCourrier());
                    row1.createCell(8).setCellValue(simu.getTauxOccupationConseiller());
                    row1.createCell(9).setCellValue(simu.getTauxOccupationPosteTel());
                    row1.createCell(10).setCellValue(score);

                    FileOutputStream fileOut;
                    try {
                      fileOut = new FileOutputStream("simu_CA.xls");
                      wb.write(fileOut);
                      fileOut.close();  
                    } catch (FileNotFoundException e) {
                      e.printStackTrace();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                }
            }
        }
    }
}
