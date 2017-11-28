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
	private int Bt; // Nb de conseillers affectés au téléphone
	private int Bc; // Nb de conseillers affectés aux courriers
	private int Qt = 0; // Nb de clients dans la file téléphonique
	private int Qc = 0; // Nb de clients dans la file courrier
	private int Nbt = 0; // Nb d'appels téléphoniques traités
	private int Nbc = 0; // Nb de courriels traités
	
	public CentreAppel(int nbConseillers, int nbConseillersTel, int nbPostesTel) {
		tempsActuel = ouverture;
		N = nbConseillers;
		Nt = nbConseillersTel;
		Nc = N - nbConseillersTel;
		T = nbPostesTel;
		Bt = Nt;
		Bc = Nc;
	}
	
	public void ajouterConseillerCourriels() {
		++Bc;
		--Bt;
	}
	
	public void enleverConseillerCourriels() {
		--Bc;
		++Bt;
	}
	
	public void incrementerFileTelephone() { ++Qt; }
	public void decrementerFileTelephone() { --Qt; }
	
	public void incrementerFileCourriel() { ++Qc; }
	public void decrementerFileCourriel() { --Qc; }
	
	public void incrementerNbAppelsTelephoniquesTraites() { ++Nbt; }
	public void incrementerNbCourrielsTraites() { ++Nbc; }
	
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

	public int getBt() {
		return Bt;
	}

	public void setBt(int bt) {
		Bt = bt;
	}

	public int getBc() {
		return Bc;
	}

	public void setBc(int bc) {
		Bc = bc;
	}

	public int getQt() {
		return Qt;
	}

	public void setQt(int qt) {
		Qt = qt;
	}

	public int getQc() {
		return Qc;
	}

	public void setQc(int qc) {
		Qc = qc;
	}

	public int getNbt() {
		return Nbt;
	}

	public void setNbt(int nbt) {
		Nbt = nbt;
	}

	public int getNbc() {
		return Nbc;
	}

	public void setNbc(int nbc) {
		Nbc = nbc;
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
