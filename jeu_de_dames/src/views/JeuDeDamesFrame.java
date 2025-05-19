package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import models.GameManager;
import java.awt.*;

public class JeuDeDamesFrame extends JFrame {
    private final int TAILLE = 10;
    private JButton[][] boutons;
    private ImageIcon pionBlanc, pionNoir, dameBlanc, dameNoir;
    private JLabel joueur1PionsLabel, joueur1DamesLabel, joueur1KillsLabel;
    private JLabel joueur2PionsLabel, joueur2DamesLabel, joueur2KillsLabel;
    private JLabel scoreLabel, turnLabel, roundLabel;
    private JButton recommencerButton, quitterButton;
    private GameManager gameManager;
    private int joueur1Pions = 20, joueur1Dames = 0, joueur1Kills = 0;
    private int joueur2Pions = 20, joueur2Dames = 0, joueur2Kills = 0;
    private Border defaultBorder = BorderFactory.createEmptyBorder();
    private Border selectedBorder = BorderFactory.createLineBorder(Color.YELLOW, 2);

    public void selectionnerCase(int i, int j) {
        boutons[i][j].setBorder(selectedBorder);
    }

    public void deselectionnerTout() {
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                boutons[i][j].setBorder(defaultBorder);
            }
        }
    }

    public JeuDeDamesFrame() {
        setTitle("Jeu de Dames - Tour : 1");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load images
        pionBlanc = redimensionnerImage("src/images/pion_blanc.png", 80, 80);
        pionNoir = redimensionnerImage("src/images/pion_noir.png", 80, 80);
        dameBlanc = redimensionnerImage("src/images/dame_blanc.jpg", 80, 80);
        dameNoir = redimensionnerImage("src/images/dame_noir.jpg", 80, 80);

        // Initialize board
        boutons = new JButton[TAILLE][TAILLE];
        JPanel boardPanel = new JPanel(new GridLayout(TAILLE, TAILLE));
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                boutons[i][j] = new JButton();
                if ((i + j) % 2 == 0) {
                    boutons[i][j].setBackground(new Color(245, 222, 179)); // Light squares
                } else {
                    boutons[i][j].setBackground(new Color(139, 69, 19)); // Dark squares
                    if (i < 4) boutons[i][j].setIcon(pionNoir);
                    else if (i > 5) boutons[i][j].setIcon(pionBlanc);
                }
                final int x = i;
                final int y = j;
                boutons[i][j].addActionListener(e -> gameManager.gererClicCase(x, y));
                boardPanel.add(boutons[i][j]);
            }
        }

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        turnLabel = new JLabel("Début de la partie : le joueur blanc commence !");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(turnLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        infoPanel.add(creerPanelJoueur("Vari (Blanc)", joueur1Pions, joueur1Dames, joueur1Kills, true));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(creerPanelJoueur("Sylva (Noir)", joueur2Pions, joueur2Dames, joueur2Kills, false));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        scoreLabel = new JLabel("Score total : 0 - 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(scoreLabel);

        roundLabel = new JLabel("Manche : 1", SwingConstants.CENTER);
        roundLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(roundLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        recommencerButton = new JButton("Recommencer");
        quitterButton = new JButton("Quitter");

        styliserBoutonClair(recommencerButton);
        styliserBoutonClair(quitterButton);

        recommencerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        recommencerButton.addActionListener(e -> gameManager.recommencerPartie());
        quitterButton.addActionListener(e -> System.exit(0));

        infoPanel.add(recommencerButton);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(quitterButton);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);

        add(mainPanel);

        // Initialize GameManager
        gameManager = new GameManager(this);
    }

    private JPanel creerPanelJoueur(String nom, int pions, int dames, int kills, boolean isFirst) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel nomLabel = new JLabel(nom);
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nomLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel pionsLabel = new JLabel("Nombre de pions : " + pions);
        JLabel damesLabel = new JLabel("Nombre de dames : " + dames);
        JLabel killsLabel = new JLabel("Nombre de kills : " + kills);

        Font font = new Font("Arial", Font.PLAIN, 13);
        pionsLabel.setFont(font);
        damesLabel.setFont(font);
        killsLabel.setFont(font);

        pionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        damesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        killsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(pionsLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(damesLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(killsLabel);

        if (isFirst) {
            joueur1PionsLabel = pionsLabel;
            joueur1DamesLabel = damesLabel;
            joueur1KillsLabel = killsLabel;
        } else {
            joueur2PionsLabel = pionsLabel;
            joueur2DamesLabel = damesLabel;
            joueur2KillsLabel = killsLabel;
        }

        return panel;
    }

    private void styliserBoutonClair(JButton bouton) {
        bouton.setFocusPainted(false);
        bouton.setBackground(new Color(210, 180, 140));
        bouton.setForeground(new Color(92, 51, 23));
        bouton.setFont(new Font("Arial", Font.BOLD, 13));
        bouton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private ImageIcon redimensionnerImage(String chemin, int largeur, int hauteur) {
        ImageIcon icon = new ImageIcon(chemin);
        Image img = icon.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public void mettreAJourCase(int i, int j, ImageIcon icon) {
        boutons[i][j].setIcon(icon);
        boutons[i][j].revalidate();
        boutons[i][j].repaint();
        System.out.println("Updated GUI case: [" + i + "][" + j + "] with " + (icon == null ? "null" : "icon"));
    }

    public void viderCase(int i, int j) {
        boutons[i][j].setIcon(null);
        boutons[i][j].revalidate();
        boutons[i][j].repaint();
        System.out.println("Cleared GUI case: [" + i + "][" + j + "]");
    }

    public void mettreAJourLabels(int j1Pions, int j1Dames, int j1Kills,
                                  int j2Pions, int j2Dames, int j2Kills,
                                  int tour) {
        joueur1PionsLabel.setText("Nombre de pions : " + j1Pions);
        joueur1DamesLabel.setText("Nombre de dames : " + j1Dames);
        joueur1KillsLabel.setText("Nombre de kills : " + j1Kills);
        joueur2PionsLabel.setText("Nombre de pions : " + j2Pions);
        joueur2DamesLabel.setText("Nombre de dames : " + j2Dames);
        joueur2KillsLabel.setText("Nombre de kills : " + j2Kills);
        turnLabel.setText("Tour du joueur : " + (tour == 1 ? "Blanc" : "Noir"));
        scoreLabel.setText("Score total : " + j1Kills + " - " + j2Kills);
        roundLabel.setText("Manche : " + ((tour + 1) / 2));
    }

    public ImageIcon getPionBlanc() {
        return pionBlanc;
    }

    public ImageIcon getPionNoir() {
        return pionNoir;
    }

    public ImageIcon getDameBlanc() {
        return dameBlanc;
    }

    public ImageIcon getDameNoir() {
        return dameNoir;
    }

    public int getTAILLE() {
        return TAILLE;
    }
}