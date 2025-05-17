package models;


public class Dame extends Pion {

    public Dame(Pion pion) {
        super(pion.getNom(),pion.getCouleur(), pion.getPosition());
        setEstDame(true); 
    }

    @Override
    public String toString() {
        return getCouleur().equals("blanc") ? "D" : "d"; // D pour dame blanche, d pour dame noire
    }
}

