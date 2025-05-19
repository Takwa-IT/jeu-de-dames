package models;

import model.modelException.PositionInvalideException;
import views.JeuDeDamesFrame;
import javax.swing.*;

public class GameManager {
    private Partie partie;
    private JeuDeDamesFrame frame;
    private Case caseSelectionnee;
    private JoueurHumain joueurBlanc;
    private JoueurHumain joueurNoir;
    private int joueur1Pions = 20, joueur1Dames = 0, joueur1Kills = 0;
    private int joueur2Pions = 20, joueur2Dames = 0, joueur2Kills = 0;
    private int tour = 1;

    public GameManager(JeuDeDamesFrame frame) {
        this.frame = frame;
        this.joueurBlanc = new JoueurHumain("Vari", "pass1", "Vari", "Joueur", "avatar1.png", "blanc");
        this.joueurNoir = new JoueurHumain("Sylva", "pass2", "Sylva", "Joueur", "avatar2.png", "noir");
        this.partie = new Partie(joueurBlanc, joueurNoir);
        mettreAJourInterface();
    }

    public void gererClicCase(int x, int y) {
        char colonne = (char) ('A' + y);
        int ligne = x + 1;
        Case caseClic = new Case(colonne, ligne);

        if (caseSelectionnee == null) {
            Plateau plateau = partie.getPlateau();
            Pion pion;
            try {
                pion = plateau.getPion(x, y);
            } catch (PositionInvalideException e) {
                JOptionPane.showMessageDialog(frame, "Erreur de position : " + e.getMessage());
                return;
            }
            if (pion != null && pion.getCouleur().equals(partie.getJoueurActuel().getCouleur())) {
                caseSelectionnee = caseClic;
                frame.deselectionnerTout();
                frame.selectionnerCase(x, y);
                System.out.println("Case sélectionnée : " + caseClic);
            } else {
                JOptionPane.showMessageDialog(frame, "Sélectionnez un pion valide pour ce joueur.");
            }
        } else {
            boolean avecPrise = estMouvementAvecPrise(caseSelectionnee, caseClic);
            try {
                if (jouerTour(caseSelectionnee, caseClic, avecPrise)) {
                    mettreAJourCompteurs();
                    mettreAJourInterface();
                    verifierFinPartie();
                    frame.deselectionnerTout();
                    caseSelectionnee = null;
                } else {
                    JOptionPane.showMessageDialog(frame, "Mouvement invalide, sélectionnez une autre case.");
                    frame.deselectionnerTout();
                    caseSelectionnee = null;
                }
            } catch (PositionInvalideException e) {
                JOptionPane.showMessageDialog(frame, "Erreur de position : " + e.getMessage());
                frame.deselectionnerTout();
                caseSelectionnee = null;
            }
        }
    }

    public boolean jouerTour(Case from, Case to, boolean avecPrise) throws PositionInvalideException {
        Plateau plateau = partie.getPlateau();
        Pion pion = plateau.getPion(from.getIndexLigne(), from.getIndexColonne());

        if (pion == null || !pion.getCouleur().equals(partie.getJoueurActuel().getCouleur())) {
            System.out.println("Pion invalide pour ce joueur.");
            return false;
        }

        Mouvement mouvement = avecPrise
                ? new MouvementAvecPrise(to, pion, plateau)
                : new MouvementSimple(to, pion, plateau);

        if (mouvement.executer()) {
            // Handle capture GUI update
            if (avecPrise) {
                Case casePionAPrendre = ((MouvementAvecPrise) mouvement).getCapturedPionCase();
                if (casePionAPrendre != null) {
                    int i = casePionAPrendre.getIndexLigne();
                    int j = casePionAPrendre.getIndexColonne();
                    frame.viderCase(i, j);
                    System.out.println("GUI cleared captured pion at " + casePionAPrendre + " ([" + i + "][" + j + "])");
                }
            }

            // Handle promotion
            if (!pion.estDame() && ((pion.getCouleur().equals("blanc") && to.getLigne() == 1)
                    || (pion.getCouleur().equals("noir") && to.getLigne() == 10))) {
                pion.promouvoir();
            }

            // Update GUI
            frame.viderCase(from.getIndexLigne(), from.getIndexColonne());
            frame.mettreAJourCase(to.getIndexLigne(), to.getIndexColonne(),
                    pion.getCouleur().equals("blanc") ?
                            (pion.estDame() ? frame.getDameBlanc() : frame.getPionBlanc()) :
                            (pion.estDame() ? frame.getDameNoir() : frame.getPionNoir()));

            partie.changerJoueur();
            tour++;
            return true;
        } else {
            System.out.println("Mouvement invalide: " + (avecPrise ? "Capture" : "Simple") + " from " + from + " to " + to);
            return false;
        }
    }

    private boolean estMouvementAvecPrise(Case depart, Case destination) {
        try {
            Plateau plateau = partie.getPlateau();
            Pion pionDepart = plateau.getPion(depart.getIndexLigne(), depart.getIndexColonne());
            if (pionDepart == null) {
                return false;
            }
            int diffLigne = destination.getLigne() - depart.getLigne();
            int diffCol = destination.getColonne() - depart.getColonne();
            if (Math.abs(diffLigne) != Math.abs(diffCol)) {
                return false;
            }
            if (!pionDepart.estDame() && Math.abs(diffLigne) != 2) {
                return false;
            }
            int dirLigne = Integer.signum(diffLigne);
            int dirCol = Integer.signum(diffCol);
            int ligneCourante = depart.getLigne() + dirLigne;
            char colCourante = (char) (depart.getColonne() + dirCol);
            while (ligneCourante != destination.getLigne() && colCourante != destination.getColonne()) {
                Case caseIntermediaire = new Case(colCourante, ligneCourante);
                Pion pionIntermediaire = plateau.getPion(caseIntermediaire.getIndexLigne(), caseIntermediaire.getIndexColonne());
                if (pionIntermediaire != null && !pionIntermediaire.getCouleur().equals(pionDepart.getCouleur())) {
                    return plateau.getPion(destination.getIndexLigne(), destination.getIndexColonne()) == null;
                }
                ligneCourante += dirLigne;
                colCourante += dirCol;
            }
            return false;
        } catch (PositionInvalideException e) {
            System.out.println("Error checking capture: " + e.getMessage());
            return false;
        }
    }

    private void mettreAJourCompteurs() {
        joueur1Pions = 0;
        joueur1Dames = 0;
        joueur2Pions = 0;
        joueur2Dames = 0;

        Plateau plateau = partie.getPlateau();
        for (int i = 0; i < plateau.TAILLE; i++) {
            for (int j = 0; j < plateau.TAILLE; j++) {
                try {
                    Pion pion = plateau.getPion(i, j);
                    if (pion != null) {
                        if (pion.getCouleur().equals("blanc")) {
                            if (pion.estDame()) {
                                joueur1Dames++;
                            } else {
                                joueur1Pions++;
                            }
                        } else {
                            if (pion.estDame()) {
                                joueur2Dames++;
                            } else {
                                joueur2Pions++;
                            }
                        }
                    }
                } catch (PositionInvalideException e) {
                    System.out.println("Erreur lors du comptage : " + e.getMessage());
                }
            }
        }
        joueur1Kills = 20 - joueur2Pions - joueur2Dames;
        joueur2Kills = 20 - joueur1Pions - joueur1Dames;
    }

    private void mettreAJourInterface() {
        frame.mettreAJourLabels(
                joueur1Pions, joueur1Dames, joueur1Kills,
                joueur2Pions, joueur2Dames, joueur2Kills,
                partie.getJoueurActuel().getCouleur().equals("blanc") ? 1 : 2
        );
        frame.setTitle("Jeu de Dames - Tour : " + tour);
    }

    private void verifierFinPartie() {
        if (joueur1Pions + joueur1Dames == 0) {
            partie.terminer();
            joueurNoir.incrementerVictoire();
            joueurBlanc.incrementerDefaite();
            JOptionPane.showMessageDialog(frame, "Joueur 2 (" + joueurNoir.getPseudonyme() + ") gagne !\n" +
                    "Victoires: " + joueurNoir.getVictoires() + ", Défaites: " + joueurNoir.getDefaites());
        } else if (joueur2Pions + joueur2Dames == 0) {
            partie.terminer();
            joueurBlanc.incrementerVictoire();
            joueurNoir.incrementerDefaite();
            JOptionPane.showMessageDialog(frame, "Joueur 1 (" + joueurBlanc.getPseudonyme() + ") gagne !\n" +
                    "Victoires: " + joueurBlanc.getVictoires() + ", Défaites: " + joueurBlanc.getDefaites());
        }
    }

    public void recommencerPartie() {
        partie.reset();
        joueur1Pions = 20;
        joueur1Dames = 0;
        joueur1Kills = 0;
        joueur2Pions = 20;
        joueur2Dames = 0;
        joueur2Kills = 0;
        tour = 1;
        caseSelectionnee = null;

        Plateau plateau = partie.getPlateau();
        for (int i = 0; i < frame.getTAILLE(); i++) {
            for (int j = 0; j < frame.getTAILLE(); j++) {
                try {
                    Pion pion = plateau.getPion(i, j);
                    if (pion != null) {
                        frame.mettreAJourCase(i, j,
                                pion.getCouleur().equals("blanc") ?
                                        (pion.estDame() ? frame.getDameBlanc() : frame.getPionBlanc()) :
                                        (pion.estDame() ? frame.getDameNoir() : frame.getPionNoir()));
                    } else {
                        frame.viderCase(i, j);
                    }
                } catch (PositionInvalideException e) {
                    System.out.println("Erreur lors de la réinitialisation : " + e.getMessage());
                }
            }
        }
        mettreAJourInterface();
    }

    public Partie getPartie() {
        return partie;
    }
}