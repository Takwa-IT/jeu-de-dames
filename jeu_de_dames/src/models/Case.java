package models;

public class Case {
    private char colonne; // exemple: 'A' à 'J'
    private int ligne;    // exemple: 1 à 10

    public Case(char colonne, int ligne) {
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public char getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setColonne(char colonne) {
        this.colonne = colonne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        return "" + colonne + ligne;
    }

    public int getIndexLigne() {
        return ligne - 1;
    }

    public int getIndexColonne() {
        return colonne - 'A';
    }

    

    @Override
    public int hashCode() {
        return colonne * 31 + ligne;
    }
    
}