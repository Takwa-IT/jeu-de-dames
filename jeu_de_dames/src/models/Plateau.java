package models;

import java.util.ArrayList;
import java.util.List;
import model.modelException.PositionInvalideException;

public class Plateau {
    private final List<List<Pion>> cases;
    public static final int TAILLE = 10;

    // ======== Constructeur ========
    public Plateau() {
        cases = new ArrayList<>();
        for (int i = 0; i < TAILLE; i++) {
            List<Pion> ligne = new ArrayList<>();
            for (int j = 0; j < TAILLE; j++) {
                ligne.add(null);
            }
            cases.add(ligne);
        }
        initialiserPlateau();
    }

    // ======== Initialisation des pions blancs et noirs ========
    private void initialiserPlateau() {
        // Noirs (lignes 0 à 3)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if ((i + j) % 2 != 0) {
                    char colonne = (char) ('A' + j);
                    int ligne = i + 1;
                    Case position = new Case(colonne, ligne);
                    String nom = "PN" + j;
                    Pion pion = new Pion(nom, "noir", position);
                    try {
                        setPion(i, j, pion);
                    } catch (PositionInvalideException e) {
                        System.out.println("Erreur position noire: " + e.getMessage());
                    }
                }
            }
        }

        // Blancs (lignes 6 à 9)
        for (int i = 6; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if ((i + j) % 2 != 0) {
                    char colonne = (char) ('A' + j);
                    int ligne = i + 1;
                    Case position = new Case(colonne, ligne);
                    String nom = "PB" + j;
                    Pion pion = new Pion(nom, "blanc", position);
                    try {
                        setPion(i, j, pion);
                    } catch (PositionInvalideException e) {
                        System.out.println("Erreur position blanche: " + e.getMessage());
                    }
                }
            }
        }
    }

    // ======== Méthodes d'accès ========
    public Pion getPion(int x, int y) throws PositionInvalideException {
        verifierPosition(x, y);
        return cases.get(x).get(y);
    }

    public void setPion(int x, int y, Pion pion) throws PositionInvalideException {
        verifierPosition(x, y);
        cases.get(x).set(y, pion);
        if (pion != null) {
            char colonne = (char) ('A' + y);
            int ligne = x + 1;
            pion.setPosition(new Case(colonne, ligne));
        }
    }

    public void supprimerPion(int x, int y) throws PositionInvalideException {
        verifierPosition(x, y);
        cases.get(x).set(y, null);
    }

    private void verifierPosition(int x, int y) throws PositionInvalideException {
        if (x < 0 || x >= TAILLE || y < 0 || y >= TAILLE) {
            throw new PositionInvalideException("Position invalide : (" + x + "," + y + ")");
        }
    }

    // ======== Affichage console temporaire ========
    public void afficherPlateau() {
    	// Affiche les lettres pour les colonnes
    	System.out.print("   ");
    	for (char c = 'A'; c < 'A' + TAILLE; c++) {
    	    System.out.print(c + " ");
    	}
    	System.out.println();

    	// Affiche les lignes numérotées
    	for (int i = 0; i < TAILLE; i++) {
    	    System.out.printf("%2d ", i + 1); // ligne = 1 → 10

    	    for (int j = 0; j < TAILLE; j++) {
    	        Pion pion = cases.get(i).get(j);
    	        System.out.print((pion == null ? ". " : pion + " "));
    	    }

    	    System.out.println();
    	}

    }}
