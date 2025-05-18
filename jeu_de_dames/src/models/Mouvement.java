package models;


import model.modelException.PositionInvalideException;

public abstract class Mouvement {
    protected Case destination;
    protected Case depart;
    protected Pion pion;
    protected Plateau plateau;

    public Mouvement(Case destination, Pion pion, Plateau plateau) {
        this.depart = pion.getPosition();
        this.destination = destination;
        this.pion = pion;
        this.plateau = plateau;
    }

    public abstract boolean executer() throws PositionInvalideException;
}

