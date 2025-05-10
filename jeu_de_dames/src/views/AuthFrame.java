package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class AuthFrame extends JFrame {

    private JTextField pseudoField, nomField, prenomField;
    private JPasswordField motDePasseField;
    private JButton btnSinscrire, btnSeConnecter, choisirAvatar;
    private JLabel avatarLabel;
    private String avatarPath = "";

    private final Color BLANC_DOUX = new Color(245, 245, 245);
    private final Color MARRON_CLAIR = new Color(210, 180, 140);
    private final Color MARRON_FONCE = new Color(101, 67, 33);
    private final Color NOIR = new Color(0, 0, 0);
    private final Color GRIS_CLAIR = new Color(230, 230, 230);

    public AuthFrame() {
        setTitle("Jeu de Dames - Inscription");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(GRIS_CLAIR);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(BLANC_DOUX);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("🎯 Bienvenue au Jeu de Dames 🎯");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(MARRON_FONCE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        mainPanel.add(createLabel("Pseudo:"), gbc);
        gbc.gridx = 1;
        pseudoField = createTextField();
        mainPanel.add(pseudoField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createLabel("Mot de passe:"), gbc);
        gbc.gridx = 1;
        motDePasseField = new JPasswordField();
        motDePasseField.setBackground(BLANC_DOUX);
        motDePasseField.setForeground(NOIR);
        mainPanel.add(motDePasseField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createLabel("Nom:"), gbc);
        gbc.gridx = 1;
        nomField = createTextField();
        mainPanel.add(nomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        prenomField = createTextField();
        mainPanel.add(prenomField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(createLabel("Avatar:"), gbc);
        gbc.gridx = 1;
        choisirAvatar = new JButton("Choisir...");
        choisirAvatar.setBackground(MARRON_CLAIR);
        choisirAvatar.setForeground(NOIR);
        mainPanel.add(choisirAvatar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        avatarLabel = new JLabel("Aucun avatar choisi.", SwingConstants.CENTER);
        avatarLabel.setForeground(MARRON_FONCE);
        mainPanel.add(avatarLabel, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(BLANC_DOUX);

        btnSinscrire = new JButton("S'inscrire");
        btnSeConnecter = new JButton("Se connecter");

        btnSinscrire.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSeConnecter.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSinscrire.setMaximumSize(new Dimension(180, 40));
        btnSeConnecter.setMaximumSize(new Dimension(180, 40));

        btnSinscrire.setBackground(MARRON_FONCE);
        btnSinscrire.setForeground(Color.WHITE);

        btnSeConnecter.setBackground(MARRON_CLAIR);
        btnSeConnecter.setForeground(NOIR);

        buttonPanel.add(btnSinscrire);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnSeConnecter);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        gbc.gridy++;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel, BorderLayout.CENTER);

        choisirAvatar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(
                    "Images", "jpg", "jpeg", "png", "gif"));

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                avatarPath = selectedFile.getAbsolutePath();
                avatarLabel.setText("Avatar choisi: " + selectedFile.getName());
            }
        });

        btnSinscrire.addActionListener((ActionEvent e) -> {
            String pseudo = pseudoField.getText().trim();
            String motDePasse = new String(motDePasseField.getPassword()).trim();
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();

            if (pseudo.isEmpty() || motDePasse.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Erreur : veuillez remplir tous les champs.",
                        "Champs vides", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Bienvenue " + pseudo + " ! Ton compte a été créé.\nAvatar : " + avatarPath,
                        "Succès", JOptionPane.INFORMATION_MESSAGE);

                new MenuFrame().setVisible(true);

                dispose();
            }
        });

        btnSeConnecter.addActionListener(e -> {
            new LoginOnlyFrame().setVisible(true);
            
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

    
}
