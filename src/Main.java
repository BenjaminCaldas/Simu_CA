

public class Main {

    public void arriveeTelephone(CentreAppel centreAppel){
        ajouterEvenement(centreAppel.getTempsActuel());

        if((centreAppel.getN() - centreAppel.getBt() - centreAppel.getBc() == 0) ||
                (centreAppel.getQt() > centreAppel.getBt() && centreAppel.getBt() < centreAppel.getT())){
            centreAppel.incrementerFileTelephone();
        }
        else{
            ajouterEvenement(centreAppel.getTempsActuel());
        }
        derniereDateSimu = dateSimu;
    }

    public void accesTelephone(CentreAppel centreAppel){
        ajouterEvenement(centreAppel.getTempsActuel());
        centreAppel.enleverConseillerCourriels();
        mettreAireAjour();
        derniereDateSimu = dateSimu;
    }

    

    public static void main(String[] args) {

        System.out.println("Hello World!");
    }
}
