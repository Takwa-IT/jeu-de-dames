package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuDeDamesFrame extends JFrame {

    private final int TAILLE = 10;
    private JButton[][] boutons;
    private ImageIcon pionBlanc;
    private ImageIcon pionNoir;

    private JLabel joueur1PionsLabel, joueur1DamesLabel, joueur1KillsLabel;
    private JLabel joueur2PionsLabel, joueur2DamesLabel, joueur2KillsLabel;
    private JLabel scoreLabel;

    private JButton recommencerButton, quitterButton;

    private int joueur1Pions = 20, joueur1Dames = 0, joueur1Kills = 0;
    private int joueur2Pions = 20, joueur2Dames = 0, joueur2Kills = 0;

    public JeuDeDamesFrame() {
        setTitle("Jeu de Dames - Tour : 1");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pionBlanc = redimensionnerImage("src/images/pion_blanc.png", 80, 80);
        pionNoir = redimensionnerImage("src/images/pion_noir.png", 80, 80);

        // Plateau
        boutons = new JButton[TAILLE][TAILLE];
        JPanel boardPanel = new JPanel(new GridLayout(TAILLE, TAILLE));
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                boutons[i][j] = new JButton();
                if ((i + j) % 2 == 0)
                    boutons[i][j].setBackground(new Color(245, 222, 179));
                else {
                    boutons[i][j].setBackground(new Color(139, 69, 19));
                    if (i < 4) boutons[i][j].setIcon(pionNoir);
                    else if (i > 5) boutons[i][j].setIcon(pionBlanc);
                }
                boutons[i][j].addActionListener(new CaseListener(i, j));  // Action pour chaque case
                boardPanel.add(boutons[i][j]);
            }
        }

        // Panneau d'infos (à droite)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel turnLabel = new JLabel("Début de la partie : le joueur blanc commence !");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(turnLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Infos des joueurs
        infoPanel.add(creerPanelJoueur("Joueur 1 : Vari", joueur1Pions, joueur1Dames, joueur1Kills, true));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        infoPanel.add(creerPanelJoueur("Joueur 2 : Sylva", joueur2Pions, joueur2Dames, joueur2Kills, false));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        scoreLabel = new JLabel("Score total : 0 - 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(scoreLabel);

        JLabel roundLabel = new JLabel("Manche : 1", SwingConstants.CENTER);
        roundLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(roundLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Boutons
        recommencerButton = new JButton("Recommencer");
        quitterButton = new JButton("Quitter");

        styliserBoutonClair(recommencerButton);
        styliserBoutonClair(quitterButton);

        recommencerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitterButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        recommencerButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "La partie va recommencer."));
        quitterButton.addActionListener(e -> System.exit(0));

        // Boutons ajoutés au panneau d'infos sans le bouton abandonner
        infoPanel.add(recommencerButton);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(quitterButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);

        add(mainPanel);
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
        
        // Dégradé de marron clair
        bouton.setBackground(new Color(210, 180, 140)); // marron clair (type "tan")
        bouton.setForeground(new Color(92, 51, 23)); // marron foncé pour le texte
        
        bouton.setFont(new Font("Arial", Font.BOLD, 13));
        bouton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        bouton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Action pour chaque case (supprimée l'affichage de JOptionPane)
    private class CaseListener implements ActionListener {
        private final int i, j;

        public CaseListener(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Code pour gérer l'action de la case sans afficher un message
            // Vous pouvez ajouter ici toute autre logique pour déplacer les pions, etc.
        }
    }

    private ImageIcon redimensionnerImage(String chemin, int largeur, int hauteur) {
        ImageIcon icon = new ImageIcon(chemin);
        Image img = icon.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

  
}
