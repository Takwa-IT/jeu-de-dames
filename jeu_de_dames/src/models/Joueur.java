package models;

public abstract class Joueur {
    protected String couleur;
    public Joueur(String couleur) {
        this.couleur = couleur;
    }
    public String getCouleur() { return couleur; }
    public abstract boolean jouerTour(Partie partie);
}
