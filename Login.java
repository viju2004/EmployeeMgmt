import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    // GUI components
    private JLabel lblTitle, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCancel;

    public Login() {
        setTitle("Employee Management System Login");
        setBounds(500, 200, 900, 700);

        lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 24));
        lblUsername.setForeground(Color.WHITE);
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 24));
        lblPassword.setForeground(Color.WHITE);
        txtUsername = new JTextField(20);
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 24));
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 24));


        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 24));

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 24));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(lblTitle, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(lblUsername, c);

        c.gridx = 1;
        c.gridy = 1;
        add(txtUsername, c);

        c.gridx = 0;
        c.gridy = 2;
        add(lblPassword, c);

        c.gridx = 1;
        c.gridy = 2;
        add(txtPassword, c);

        c.gridx = 0;
        c.gridy = 3;
        add(btnLogin, c);

        c.gridx = 1;
        c.gridy = 3;
        add(btnCancel, c);

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
               if (e.getSource() == btnLogin) {
            System.out.println("Button called");
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            System.out.println(""+username+" "+password);
                       if(username.equals("admin") && password.equals("admin123"))
            {
                System.out.println("ssjabchjsbc");
                new EmployeeInfo();
            }
            else
            {  JOptionPane.showMessageDialog(this, " Incorrect Login Details ");
            }

        } else if (e.getSource() == btnCancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
