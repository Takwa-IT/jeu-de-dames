package models;

public class Pion {
    private String nom;
    private String couleur;
    private Case position;
    private boolean estDame;

    public Pion(String nom, String couleur, Case position) {
        this.nom = nom;
        this.couleur = couleur;
        this.position = position;
        this.estDame = false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public boolean estDame() {
        return estDame;
    }

    public void setEstDame(boolean estDame) {
        this.estDame = estDame;
    }

    public void promouvoir() {
        this.estDame = true;
    }

    @Override
    public String toString() {
        return estDame ? (couleur.equals("blanc") ? "D" : "d") : (couleur.equals("blanc") ? "B" : "N");
    }}