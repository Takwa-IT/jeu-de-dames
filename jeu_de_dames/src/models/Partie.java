package models;


public class Partie {
    private Plateau plateau;
    private Joueur joueurBlanc;
    private Joueur joueurNoir;
    private Joueur joueurActuel;
    private boolean terminee;

    public Partie(Joueur joueurBlanc, Joueur joueurNoir) {
        this.plateau = new Plateau();
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;
        this.joueurActuel = joueurBlanc; // Blanc commence
        this.terminee = false;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public void changerJoueur() {
        joueurActuel = joueurActuel == joueurBlanc ? joueurNoir : joueurBlanc;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void terminer() {
        this.terminee = true;
    }

    public void reset() {
        this.plateau = new Plateau();
        this.joueurActuel = joueurBlanc;
        this.terminee = false;
    }
} 

