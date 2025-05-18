package models;

import model.modelException.PositionInvalideException;

public class MouvementAvecPrise extends Mouvement {

    public MouvementAvecPrise(Case destination, Pion pion, Plateau plateau) {
        super(destination, pion, plateau);
    }

    @Override
    public boolean executer() throws PositionInvalideException {
        int ligneDepart = depart.getLigne();
        char colDepart = depart.getColonne();
        int ligneDest = destination.getLigne();
        char colDest = destination.getColonne();

        int diffLigne = ligneDest - ligneDepart;
        int diffCol = colDest - colDepart;

        // ----- Cas Pion normal : saut de 2 cases -----
        if (!pion.estDame()) {
            if (Math.abs(diffLigne) == 2 && Math.abs(diffCol) == 2) {
                // Vérifier direction valide selon la couleur
               if ((pion.getCouleur().equals("noir") && diffLigne != 2) ||
    (pion.getCouleur().equals("blanc") && diffLigne != -2)) {
    return false;
}


                int ligneMilieu = (ligneDepart +ligneDest) / 2;
                char colMilieu = (char) ((colDepart + colDest) / 2);

                Case caseMilieu = new Case(colMilieu, ligneMilieu);

                Pion pionAuMilieu = plateau.getPion(caseMilieu.getIndexLigne(), caseMilieu.getIndexColonne());

                if (pionAuMilieu != null
                        && !pionAuMilieu.getCouleur().equals(pion.getCouleur())
                        && plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) == null) {

                    plateau.supprimerPion(caseMilieu.getIndexLigne(), caseMilieu.getIndexColonne());
                    pion.setPosition(destination);
                    return true;
                }
            }
        } 

        // ----- Cas Dame : saut de plusieurs cases en diagonale -----
        else {
            int dirLigne = Integer.signum(diffLigne);
            int dirCol = Integer.signum(diffCol);

            int ligneCourante = ligneDepart + dirLigne;
            char colCourante = (char) (colDepart + dirCol);

            boolean pionTrouve = false;
            Case casePionAPrendre = null;

            while (ligneCourante != ligneDest && colCourante != colDest) {
                Case caseIntermediaire = new Case(colCourante, ligneCourante);
                Pion pionIntermediaire = plateau.getPion(caseIntermediaire.getIndexLigne(), caseIntermediaire.getIndexColonne());

                if (pionIntermediaire != null) {
                    if (pionIntermediaire.getCouleur().equals(pion.getCouleur()) || pionTrouve) {
                        return false; // soit pion allié soit plus d'un pion à sauter
                    }
                    pionTrouve = true;
                    casePionAPrendre = caseIntermediaire;
                }

                ligneCourante += dirLigne;
                colCourante += dirCol;
            }

            if (pionTrouve && plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) == null) {
                plateau.supprimerPion(casePionAPrendre.getIndexLigne(), casePionAPrendre.getIndexColonne());
                pion.setPosition(destination);
                return true;
            }
        }

        return false;
    }
}

