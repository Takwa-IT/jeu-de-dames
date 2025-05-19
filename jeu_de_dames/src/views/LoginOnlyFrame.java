package views;

import javax.swing.*;

import controllers.UtilisateurController;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginOnlyFrame extends JFrame {
  /*  private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    private final Color BLANC_DOUX = new Color(245, 245, 245);
    private final Color MARRON_CLAIR = new Color(210, 180, 140);
    private final Color MARRON_FONCE = new Color(101, 67, 33);
    private final Color NOIR = new Color(0, 0, 0);

    public LoginOnlyFrame() {
        setTitle("Connexion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BLANC_DOUX);
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("🔐 Connexion au Jeu de Dames", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(MARRON_FONCE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Panel formulaire
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BLANC_DOUX);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champ Pseudo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("Pseudo:"), gbc);
        gbc.gridx = 1;
        usernameField = createTextField();
        formPanel.add(usernameField, gbc);

        // Champ Mot de passe
        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(createLabel("Mot de passe:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setBackground(BLANC_DOUX);
        passwordField.setForeground(NOIR);
        formPanel.add(passwordField, gbc);

        // Bouton Connexion
        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Se connecter");
        loginButton.setBackground(MARRON_CLAIR);
        loginButton.setForeground(NOIR);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 35));
        formPanel.add(loginButton, gbc);

        // Message
        gbc.gridy++;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        formPanel.add(messageLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Action
        loginButton.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                messageLabel.setText("Veuillez remplir tous les champs.");
            } else {
            	try {
            	    UtilisateurController controller = new UtilisateurController();
            	    boolean isAuthenticated = controller.verifierConnexion(username, password);

            	    if (isAuthenticated) {
            	        JOptionPane.showMessageDialog(this,
            	                "Connexion réussie, bienvenue " + username + " !",
            	                "Succès", JOptionPane.INFORMATION_MESSAGE);

            	        new MenuFrame().setVisible(true);
            	        dispose();
            	    } else {
            	        messageLabel.setText("Pseudo ou mot de passe incorrect.");
            	    }
            	} catch (Exception ex) {
            	    ex.printStackTrace();
            	    JOptionPane.showMessageDialog(this,
            	            "Erreur lors de la connexion : " + ex.getMessage(),
            	            "Erreur", JOptionPane.ERROR_MESSAGE);

            	}
            }

        });

    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(NOIR);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setBackground(BLANC_DOUX);
        field.setForeground(NOIR);
        return field;
    }

   */ 
}
