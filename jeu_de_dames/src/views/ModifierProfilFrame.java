package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

public class ModifierProfilFrame extends JFrame {
	private JTextField pseudoField, nomField, prenomField;
	private JPasswordField nouveauMdpField;
	private JLabel avatarPreview;
	private String avatarPath = "";

	private final Color BLANC = new Color(255, 255, 255);
	private final Color MARRON_CLAIR = new Color(210, 180, 140);
	private final Color MARRON_FONCE = new Color(101, 67, 33);
	private final Color NOIR = new Color(0, 0, 0);

	public ModifierProfilFrame() {
	    setTitle("👤 Modifier Profil");
	    setSize(450, 500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    getContentPane().setBackground(BLANC);

	    JPanel panel = new JPanel(new GridBagLayout());
	    panel.setBackground(BLANC);
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(12, 20, 12, 20);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    Font labelFont = new Font("SansSerif", Font.BOLD, 14);

	    // Pseudo
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    JLabel pseudoLabel = new JLabel("Pseudo:");
	    pseudoLabel.setFont(labelFont);
	    pseudoLabel.setForeground(NOIR);
	    panel.add(pseudoLabel, gbc);
	    gbc.gridx = 1;
	    pseudoField = new JTextField();
	    panel.add(pseudoField, gbc);

	    // Nom
	    gbc.gridx = 0;
	    gbc.gridy++;
	    JLabel nomLabel = new JLabel("Nom:");
	    nomLabel.setFont(labelFont);
	    nomLabel.setForeground(NOIR);
	    panel.add(nomLabel, gbc);
	    gbc.gridx = 1;
	    nomField = new JTextField();
	    panel.add(nomField, gbc);

	    // Prénom
	    gbc.gridx = 0;
	    gbc.gridy++;
	    JLabel prenomLabel = new JLabel("Prénom:");
	    prenomLabel.setFont(labelFont);
	    prenomLabel.setForeground(NOIR);
	    panel.add(prenomLabel, gbc);
	    gbc.gridx = 1;
	    prenomField = new JTextField();
	    panel.add(prenomField, gbc);

	    // Nouveau mot de passe
	    gbc.gridx = 0;
	    gbc.gridy++;
	    JLabel mdpLabel = new JLabel("Nouveau mot de passe:");
	    mdpLabel.setFont(labelFont);
	    mdpLabel.setForeground(NOIR);
	    panel.add(mdpLabel, gbc);
	    gbc.gridx = 1;
	    nouveauMdpField = new JPasswordField();
	    panel.add(nouveauMdpField, gbc);

	    // Avatar actuel
	    gbc.gridx = 0;
	    gbc.gridy++;
	    JLabel avatarLabel = new JLabel("Avatar actuel:");
	    avatarLabel.setFont(labelFont);
	    avatarLabel.setForeground(NOIR);
	    panel.add(avatarLabel, gbc);
	    gbc.gridx = 1;
	    avatarPreview = new JLabel("Aucun");
	    avatarPreview.setForeground(MARRON_FONCE);
	    panel.add(avatarPreview, gbc);

	    // Bouton changer avatar
	    gbc.gridx = 1;
	    gbc.gridy++;
	    JButton choisirAvatar = new JButton("Changer Avatar");
	    choisirAvatar.setBackground(MARRON_CLAIR);
	    choisirAvatar.setForeground(Color.BLACK);
	    panel.add(choisirAvatar, gbc);

	    choisirAvatar.addActionListener(e -> {
	        JFileChooser chooser = new JFileChooser();
	        chooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "jpeg"));
	        int result = chooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selected = chooser.getSelectedFile();
	            avatarPath = selected.getAbsolutePath();
	            avatarPreview.setText(selected.getName());
	        }
	    });

	    // Boutons enregistrer / annuler
	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.gridwidth = 2;
	    JPanel buttons = new JPanel();
	    buttons.setBackground(BLANC);
	    JButton enregistrer = new JButton("💾 Enregistrer");
	    JButton annuler = new JButton("✖️ Annuler");
	    enregistrer.setBackground(MARRON_FONCE);
	    enregistrer.setForeground(Color.WHITE);
	    annuler.setBackground(Color.LIGHT_GRAY);

	    enregistrer.setFont(new Font("SansSerif", Font.BOLD, 13));
	    annuler.setFont(new Font("SansSerif", Font.PLAIN, 13));

	    buttons.add(enregistrer);
	    buttons.add(annuler);
	    panel.add(buttons, gbc);

	    enregistrer.addActionListener(this::enregistrerModifications);
	    annuler.addActionListener(e -> dispose());

	    add(panel);
	}

	private void enregistrerModifications(ActionEvent e) {
	    String pseudo = pseudoField.getText().trim();
	    String nom = nomField.getText().trim();
	    String prenom = prenomField.getText().trim();
	    String mdp = new String(nouveauMdpField.getPassword()).trim();

	    if (pseudo.isEmpty() || nom.isEmpty() || prenom.isEmpty() || mdp.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(this, "Profil modifié avec succès 🎉", "Succès", JOptionPane.INFORMATION_MESSAGE);
	        dispose();
	        // Appeler ici le controller pour sauvegarder en base de données
	    }
	}

}