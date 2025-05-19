package models;

import model.modelException.PositionInvalideException;

public class MouvementAvecPrise extends Mouvement {
    private Case capturedPionCase; // Store the captured pion's case

    public MouvementAvecPrise(Case destination, Pion pion, Plateau plateau) {
        super(destination, pion, plateau);
        this.capturedPionCase = null;
    }

    @Override
    public boolean executer() throws PositionInvalideException {
        int ligneDepart = depart.getLigne();
        char colDepart = depart.getColonne();
        int ligneDest = destination.getLigne();
        char colDest = destination.getColonne();

        int diffLigne = ligneDest - ligneDepart;
        int diffCol = colDest - colDepart;

        // Check if move is diagonal
        if (Math.abs(diffLigne) != Math.abs(diffCol)) {
            System.out.println("Capture failed: Not diagonal");
            return false;
        }

        // ----- Cas Pion normal : saut de 2 cases -----
        if (!pion.estDame()) {
            if (Math.abs(diffLigne) == 2 && Math.abs(diffCol) == 2) {
                int ligneMilieu = (ligneDepart + ligneDest) / 2;
                char colMilieu = (char) ((colDepart + colDest) / 2);
                Case caseMilieu = new Case(colMilieu, ligneMilieu);
                Pion pionAuMilieu = plateau.getPion(caseMilieu.getIndexLigne(), caseMilieu.getIndexColonne());
                if (pionAuMilieu != null
                        && !pionAuMilieu.getCouleur().equals(pion.getCouleur())
                        && plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) == null) {
                    capturedPionCase = caseMilieu;
                    plateau.supprimerPion(caseMilieu.getIndexLigne(), caseMilieu.getIndexColonne());
                    pion.setPosition(destination);
                    plateau.supprimerPion(depart.getIndexLigne(), depart.getIndexColonne());
                    plateau.setPion(destination.getIndexLigne(), destination.getIndexColonne(), pion);
                    System.out.println("Capture executed: Removed pion at " + caseMilieu);
                    return true;
                } else {
                    System.out.println("Capture failed: Middle=" + (pionAuMilieu != null ? pionAuMilieu.getCouleur() : "null") +
                                       ", Destination=" + (plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) != null ? "occupied" : "empty"));
                    return false;
                }
            }
            System.out.println("Capture failed: Invalid distance for pion");
            return false;
        } 
        // ----- Cas Dame : saut de plusieurs cases en diagonale -----
        else {
            int dirLigne = Integer.signum(diffLigne);
            int dirCol = Integer.signum(diffCol);
            int ligneCourante = ligneDepart + dirLigne;
            char colCourante = (char) (colDepart + dirCol);
            Case casePionAPrendre = null;
            int opponentCount = 0;
            while (ligneCourante != ligneDest && colCourante != colDest) {
                Case caseIntermediaire = new Case(colCourante, ligneCourante);
                Pion pionIntermediaire = plateau.getPion(caseIntermediaire.getIndexLigne(), caseIntermediaire.getIndexColonne());
                if (pionIntermediaire != null) {
                    if (pionIntermediaire.getCouleur().equals(pion.getCouleur())) {
                        System.out.println("Capture failed: Friendly pion at " + caseIntermediaire);
                        return false;
                    }
                    opponentCount++;
                    if (opponentCount > 1) {
                        System.out.println("Capture failed: Multiple opponent pions on diagonal");
                        return false;
                    }
                    casePionAPrendre = caseIntermediaire;
                }
                ligneCourante += dirLigne;
                colCourante += dirCol;
            }
            if (opponentCount == 1 && plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) == null) {
                capturedPionCase = casePionAPrendre;
                plateau.supprimerPion(casePionAPrendre.getIndexLigne(), casePionAPrendre.getIndexColonne());
                pion.setPosition(destination);
                plateau.supprimerPion(depart.getIndexLigne(), depart.getIndexColonne());
                plateau.setPion(destination.getIndexLigne(), destination.getIndexColonne(), pion);
                System.out.println("Dame capture executed: Removed pion at " + casePionAPrendre);
                return true;
            }
            System.out.println("Capture failed: " + (opponentCount == 0 ? "No opponent pion" : "Destination occupied"));
            return false;
        }
    }

    public Case getCapturedPionCase() {
        return capturedPionCase;
    }
}