import javax.swing.*;
import java.awt.*;

public class FrontPage extends JFrame {

    public FrontPage() {

        setTitle("Employee Management System");
        setSize(1100, 900);
        setLocationRelativeTo(null);
//        getContentPane().setForeground(Color.GRAY);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load logo image
        ImageIcon logo = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logo);

        logoLabel.setVisible(true);

        JLabel welcomeLabel = new JLabel("Welcome to Employee Management System");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 35));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);

        JLabel descriptionLabel = new JLabel("This system allows you to manage your employees.");
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        descriptionLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 30, 0);
        panel.add(logoLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 10, 0);
        panel.add(welcomeLabel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 50, 0);
        panel.add(descriptionLabel, c);

        JButton startButton = new JButton("Get Started");
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        startButton.setBackground(new Color(0, 128, 255));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> {
                       new Login();
        });

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(startButton, c);

        getContentPane().add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrontPage();
    }
}
