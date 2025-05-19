package models;

import model.modelException.PositionInvalideException;

public class MouvementSimple extends Mouvement {
    public MouvementSimple(Case destination, Pion pion, Plateau plateau) {
        super(destination, pion, plateau);
    }

    @Override
    public boolean executer() throws PositionInvalideException {
        int ligneDepart = depart.getLigne();
        char colDepart = depart.getColonne();
        int ligneDest = destination.getLigne();
        char colDest = destination.getColonne();

        // ----------- Cas Pion simple -----------
        if (!pion.estDame()) {
            int ligneValide = pion.getCouleur().equals("noir") ? ligneDepart + 1 : ligneDepart - 1;
            char colGauche = (char) (colDepart - 1);
            char colDroite = (char) (colDepart + 1);

            if ((colDest == colGauche || colDest == colDroite)
                    && colDest >= 'A' && colDest <= 'J'
                    && ligneDest >= 1 && ligneDest <= 10
                    && ligneDest == ligneValide) {

                if (plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) != null) {
                    return false; // destination occupée
                }

                pion.setPosition(destination);
                plateau.supprimerPion(depart.getIndexLigne(), depart.getIndexColonne());
                plateau.setPion(destination.getIndexLigne(), destination.getIndexColonne(), pion);
                return true;
            }
        }
        // ----------- Cas Dame -----------
        else {
            int diffLigne = Math.abs(ligneDest - ligneDepart);
            int diffCol = Math.abs(colDest - colDepart);

            if (diffLigne == diffCol && diffLigne >= 1) {
                if (ligneDest < 1 || ligneDest > 10 || colDest < 'A' || colDest > 'J') {
                    return false;
                }

                if (plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) != null) {
                    return false;
                }

                pion.setPosition(destination);
                plateau.supprimerPion(depart.getIndexLigne(), depart.getIndexColonne());
                plateau.setPion(destination.getIndexLigne(), destination.getIndexColonne(), pion);
                return true;
            }
        }
        return false;
    }
}