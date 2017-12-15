package Simu;

public class CentreAppel {

	/* Temps */
	private int ouverture = 0; // Ouvre à 9h -> Temps = 0s
	private int tempsActuel; // Temps à l'instant donné
	private int fermeture = 14400; // Ferme à 12h -> Temps = 14400s

	/* Ressources */
	private int N; // Nb d'employés
	private int Nt; // Nb de conseillers affectés au téléphone
	private int Nc; // Nb de conseillers affectés aux courriers
	private int T; // Nb de postes téléphoniques

	/* Variables */
	private int conseillerTelephone; // Nb de conseillers affectés au téléphone
	private int conseillerCourriel; // Nb de conseillers affectés aux courriers
	private int nbClientQueueTelephone = 0; // Nb de clients dans la file téléphonique
	private int nbClientQueueCourriel = 0; // Nb de clients dans la file courrier
	private int nbAppelTraites = 0; // Nb d'appels téléphoniques traités
	private int nbCourrielTraites = 0; // Nb de courriels traités

	public CentreAppel(int nbConseillers, int nbConseillersTel, int nbPostesTel) {
		tempsActuel = ouverture;
		N = nbConseillers;
		Nt = nbConseillersTel;
		Nc = N - nbConseillersTel;
		T = nbPostesTel;
		conseillerTelephone = Nt;
		conseillerCourriel = Nc;
	}

	public void incrementerFileTelephone() { ++nbClientQueueTelephone; }
	public void decrementerFileTelephone() { --nbClientQueueTelephone; }

	public void incrementerFileCourriel() { ++nbClientQueueCourriel; }
	public void decrementerFileCourriel() { --nbClientQueueCourriel; }

	public void incrementerNbAppelsTelephoniquesTraites() { ++nbAppelTraites; }
	public void incrementerNbCourrielsTraites() { ++nbCourrielTraites; }

	public boolean verifierTemps() {
		if (tempsActuel < fermeture) return true;
		else return false;
	}

	public int getTempsActuel() {
		return tempsActuel;
	}

	public void setTempsActuel(int tempsActuel) {
		this.tempsActuel = tempsActuel;
	}

	public int getConseillerTelephone() {
		return conseillerTelephone;
	}

	public void setConseillerTelephone(int bt) {
		conseillerTelephone = bt;
	}

	public int getConseillerCourriel() {
		return conseillerCourriel;
	}

	public void setConseillerCourriel(int bc) {
		conseillerCourriel = bc;
	}

	public int getNbClientQueueTelephone() {
		return nbClientQueueTelephone;
	}

	public void setNbClientQueueTelephone(int qt) {
		nbClientQueueTelephone = qt;
	}

	public int getNbClientQueueCourriel() {
		return nbClientQueueCourriel;
	}

	public void setNbClientQueueCourriel(int qc) {
		nbClientQueueCourriel = qc;
	}

	public int getNbAppelTraites() {
		return nbAppelTraites;
	}

	public void setNbAppelTraites(int nbt) {
		nbAppelTraites = nbt;
	}

	public int getNbCourrielTraites() {
		return nbCourrielTraites;
	}

	public void setNbCourrielTraites(int nbc) {
		nbCourrielTraites = nbc;
	}

	public int getOuverture() {
		return ouverture;
	}

	public int getFermeture() {
		return fermeture;
	}

	public int getN() {
		return N;
	}

	public int getNt() {
		return Nt;
	}

	public int getNc() {
		return Nc;
	}

	public int getT() {
		return T;
	}
}