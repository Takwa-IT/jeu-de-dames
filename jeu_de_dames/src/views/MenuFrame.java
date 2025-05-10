package views;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuFrame extends JFrame {
	
	private final Color BLANC = new Color(255, 255, 255);
	private final Color MARRON_CLAIR = new Color(210, 180, 140);
	private final Color MARRON_FONCE = new Color(101, 67, 33);
	private final Color NOIR = new Color(0, 0, 0);

	public MenuFrame() {
	    setTitle("🎯 Menu Principal - Jeu de Dames");
	    setSize(450, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    getContentPane().setBackground(BLANC);

	    JPanel mainPanel = new JPanel(new GridLayout(6, 1, 15, 15));
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
	    mainPanel.setBackground(BLANC);

	    JLabel titleLabel = new JLabel("Bienvenue au Menu", SwingConstants.CENTER);
	    titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
	    titleLabel.setForeground(MARRON_FONCE);

	    JButton nouvellePartieBtn = createStyledButton("🆕 Nouvelle Partie");
	    JButton reprendreBtn = createStyledButton("⏸️ Reprendre Partie");
	    JButton statsBtn = createStyledButton("📊 Statistiques");
	    JButton modifierProfilBtn = createStyledButton("👤 Modifier Profil");
	    JButton quitterBtn = createStyledButton("🚪 Quitter");

	    quitterBtn.setBackground(Color.RED);
	    quitterBtn.setForeground(Color.WHITE);
	    quitterBtn.addActionListener(e -> System.exit(0));
	    modifierProfilBtn.addActionListener(e -> new ModifierProfilFrame().setVisible(true));
	    statsBtn.addActionListener(e -> {
	    	new StatistiquesFrame("takwa99", 40, 22, 18, 55.0, "2024-10-12", 5).setVisible(true);
	    	});
	    
	    nouvellePartieBtn.addActionListener(e -> {
            new JeuDeDamesFrame().setVisible(true); // Lancement de la nouvelle partie
            dispose(); // Ferme la fenêtre du menu
        });

        // Action pour Reprendre Partie
        reprendreBtn.addActionListener(e -> {
            new JeuDeDamesFrame().setVisible(true); // Reprendre la partie précédente ou nouvelle instance
            dispose(); // Ferme la fenêtre du menu
        });
	    

	    mainPanel.add(titleLabel);
	    mainPanel.add(nouvellePartieBtn);
	    mainPanel.add(reprendreBtn);
	    mainPanel.add(statsBtn);
	    mainPanel.add(modifierProfilBtn);
	    mainPanel.add(quitterBtn);
	    add(mainPanel);
	}

	private JButton createStyledButton(String text) {
	    JButton btn = new JButton(text);
	    btn.setFont(new Font("SansSerif", Font.BOLD, 14));
	    btn.setBackground(MARRON_CLAIR);
	    btn.setForeground(NOIR);
	    btn.setFocusPainted(false);
	    btn.setBorder(BorderFactory.createLineBorder(MARRON_FONCE, 2));
	    return btn;
	}

	
}
