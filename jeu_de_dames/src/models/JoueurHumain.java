package models;


	public class JoueurHumain extends Joueur {
	    private String pseudonyme;
	    private String motDePasse;
	    private String nom;
	    private String prenom;
	    private String avatar;
	    private int victoires, defaites, partiesNulles;

	    public JoueurHumain(String pseudonyme, String motDePasse, String nom, String prenom, String avatar, String couleur) {
	        super(couleur);
	        this.pseudonyme = pseudonyme;
	        this.motDePasse = motDePasse;
	        this.nom = nom;
	        this.prenom = prenom;
	        this.avatar = avatar;
	    }
	    

	    @Override
	    public boolean jouerTour(Partie partie) {
	        // Le tour est déclenché via l’interface graphique
	        return false;
	    }
	    public String getPseudonyme() {
	        return pseudonyme;
	    }

	    public void setPseudonyme(String pseudonyme) {
	        this.pseudonyme = pseudonyme;
	    }

	    public String getMotDePasse() {
	        return motDePasse;
	    }

	    public void setMotDePasse(String motDePasse) {
	        this.motDePasse = motDePasse;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public String getPrenom() {
	        return prenom;
	    }

	    public String getAvatar() {
	        return avatar;
	    }

	    public void setAvatar(String avatar) {
	        this.avatar = avatar;
	    }

	    public int getVictoires() {
	        return victoires;
	    }

	    public int getDefaites() {
	        return defaites;
	    }

	    public int getPartiesNulles() {
	        return partiesNulles;
	    }

	    // ======= Méthodes utiles =======
	    public void incrementerVictoire() {
	        this.victoires++;
	    }

	    public void incrementerDefaite() {
	        this.defaites++;
	    }

	    public void incrementerPartieNulle() {
	        this.partiesNulles++;
	    }

	    @Override
	    public String toString() {
	        return prenom + " " + nom + " (" + pseudonyme + ")";
	    }
	}
