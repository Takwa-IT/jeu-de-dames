package views;

import javax.swing.*;
import java.awt.*;

public class StatistiquesFrame extends JFrame {
    private final Color BLANC_CASSEROLE = new Color(250, 250, 250); // Fond de la fenêtre
    private final Color MARRON_CLAIR = new Color(210, 180, 140);
    private final Color MARRON_FONCE = new Color(120, 72, 48);
    private final Color CARTE_FOND = new Color(245, 245, 245); // Fond des cartes
    private final Font FONT_TITRE = new Font("SansSerif", Font.BOLD, 22);
    private final Font FONT_CARTE = new Font("SansSerif", Font.PLAIN, 15);
    private final Color BOUTON_MARRON_FONCE = new Color(210, 180, 140); // Moins foncé pour le bouton
    private final Color CONTOUR_MARRON = new Color(140, 90, 60);

    public StatistiquesFrame(String pseudo, int partiesJouees, int victoires, int defaites,
                             double tauxReussite, String dateInscription, int classement) {

        setTitle("📊 Statistiques de " + pseudo);
        setSize(420, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(BLANC_CASSEROLE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(BLANC_CASSEROLE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        // Titre
        JLabel titre = new JLabel("🎯 Statistiques du Joueur", SwingConstants.CENTER);
        titre.setFont(FONT_TITRE);
        titre.setForeground(MARRON_FONCE);
        mainPanel.add(titre, BorderLayout.NORTH);

        // Centre : liste de cartes
        JPanel statsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        statsPanel.setBackground(BLANC_CASSEROLE);

        statsPanel.add(createStatCard("👤 ", "Pseudo", pseudo));
        statsPanel.add(createStatCard("📅 ", "Parties jouées", String.valueOf(partiesJouees)));
        statsPanel.add(createStatCard("🏆 ", "Victoires", String.valueOf(victoires)));
        statsPanel.add(createStatCard("❌ ", "Défaites", String.valueOf(defaites)));
        statsPanel.add(createStatCard("📊 ", "Taux de réussite", tauxReussite + " %"));
        statsPanel.add(createStatCard("🕐 ", "Inscrit depuis", dateInscription));
        statsPanel.add(createStatCard("🥇 ", "Classement", String.valueOf(classement)));

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // Bouton retour avec survol
        JButton retourBtn = new JButton("↩ Retour au menu");
        retourBtn.setBackground(BOUTON_MARRON_FONCE);
        retourBtn.setForeground(Color.BLACK);
        retourBtn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        retourBtn.setFocusPainted(false);
        retourBtn.setBorderPainted(false);
        retourBtn.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(BLANC_CASSEROLE);
        btnPanel.add(retourBtn);

        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createStatCard(String icon, String label, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARTE_FOND);
        card.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, CONTOUR_MARRON)); // Ligne fine en bas

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        iconLabel.setForeground(MARRON_FONCE);

        JLabel textLabel = new JLabel();
        textLabel.setFont(FONT_CARTE);
        String styledText = "<html><span style='color:rgb(160,100,60);'>" + label + " :</span> " +
                            "<span style='color:black; font-weight:bold;'>" + value + "</span></html>";
        textLabel.setText(styledText);

        card.add(iconLabel, BorderLayout.WEST);
        card.add(textLabel, BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StatistiquesFrame("takwa99", 40, 22, 18, 55.0, "2024-10-12", 5).setVisible(true);
        });
    }
}
