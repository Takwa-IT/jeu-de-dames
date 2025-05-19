package dao;

import models.Joueur;
import utils.ConnexionDB;

import java.sql.*;

public class JoueurDAO {
  /*  private Connection conn;

    public JoueurDAO() {
        conn = ConnexionDB.getConnection();
        if (conn == null) {
            throw new RuntimeException("Échec de connexion à la base de données !");
        }
    }

    public boolean pseudoExiste(String pseudo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM joueurs WHERE pseudo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pseudo);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public void ajouterJoueur(Joueur joueur) throws SQLException {
        String sql = "INSERT INTO joueurs (pseudo, mot_de_passe, nom, prenom, avatar) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, joueur.getPseudonyme());
            stmt.setString(2, joueur.getMotDePasse());
            stmt.setString(3, joueur.getNom());
            stmt.setString(4, joueur.getPrenom());
            stmt.setString(5, joueur.getAvatar());
            stmt.executeUpdate();
        }
    }
    
    public Joueur trouverJoueurParPseudoEtMotDePasse(String pseudo, String motDePasseHash) throws SQLException {
        String sql = "SELECT * FROM joueurs WHERE pseudo = ? AND mot_de_passe = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pseudo);
            stmt.setString(2, motDePasseHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Joueur(
                    rs.getString("pseudo"),
                    rs.getString("mot_de_passe"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("avatar")
                );
            }
        }
        return null;
    }*/

}
