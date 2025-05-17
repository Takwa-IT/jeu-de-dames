
package controllers;

import dao.JoueurDAO;
import models.Joueur;
import utils.HashUtils;

public class UtilisateurController {

    private JoueurDAO joueurDAO;

    public UtilisateurController() throws Exception {
        joueurDAO = new JoueurDAO();
    }

    /**
     * Vérifie si un pseudo existe déjà dans la base.
     */
    public boolean pseudoExiste(String pseudo) {
        try {
            return joueurDAO.pseudoExiste(pseudo);
        } catch (Exception e) {
            e.printStackTrace();
            return true; // En cas d'erreur, mieux vaut bloquer l'inscription
        }
    }

    /**
     * Crée un nouveau joueur dans la base de données.
     */
    public boolean inscrireNouvelUtilisateur(String pseudo, String motDePasse, String nom, String prenom, String avatarPath) {
        try {
        	String motDePasseHash = HashUtils.sha256(motDePasse);
        	Joueur joueur = new Joueur(pseudo, motDePasseHash, nom, prenom, avatarPath);
            joueurDAO.ajouterJoueur(joueur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean verifierConnexion(String pseudo, String motDePasse) {
        try {
            String motDePasseHash = HashUtils.sha256(motDePasse);
            Joueur joueur = joueurDAO.trouverJoueurParPseudoEtMotDePasse(pseudo, motDePasseHash);
            return joueur != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
