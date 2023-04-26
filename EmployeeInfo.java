import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//for connect database
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class EmployeeInfo extends JFrame implements ActionListener {

    private JLabel empid, nameLabel, salaryLabel, mobileLabel, emailLabel,titlel,titlen;
    private JTextField empidField, nameField, salaryField, mobileField, emailField;
    private JButton saveButton, displayButton, clearButton;
    private JCheckBox deleteCheckbox;
    private JButton deleteButton;
    private JButton searchButton;

    public EmployeeInfo() {
        // Set up the frame
        super("Employee  Information");
        setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 900);
        setLocationRelativeTo(null);


        // Create the labels and text fields
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font fieldFont = new Font("Arial", Font.BOLD, 22);
        Font titleFont = new Font("Arial", Font.BOLD, 25);

        titlel = new JLabel("Employee Management System :");
        titlel.setFont(titleFont);
        titlel.setForeground(Color.WHITE);
        titlen = new JLabel(" ");
        titlen.setFont(titleFont);
        titlen.setForeground(Color.WHITE);

        empid = new JLabel("Employee ID:");
        empid.setForeground(Color.WHITE);
        empid.setFont(labelFont);
        empid.setHorizontalAlignment(SwingConstants.CENTER);

        empidField = new JTextField(20);
        empidField.setFont(fieldFont);
        empidField.setForeground(Color.WHITE);
        empidField.setBackground(Color.GRAY);



        nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);



        nameField = new JTextField(30);
        nameField.setFont(fieldFont);
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(Color.GRAY);

        salaryLabel = new JLabel("Salary :");
        salaryLabel.setFont(labelFont);
        salaryLabel.setForeground(Color.WHITE);
        salaryLabel.setHorizontalAlignment(SwingConstants.CENTER);



        salaryField = new JTextField(30);
        salaryField.setFont(fieldFont);
        salaryField.setForeground(Color.WHITE);
        salaryField.setBackground(Color.GRAY);

        mobileLabel = new JLabel("Mobile:");
        mobileLabel.setFont(labelFont);
        mobileLabel.setForeground(Color.WHITE);
        mobileLabel.setHorizontalAlignment(SwingConstants.CENTER);



        mobileField = new JTextField(20);
        mobileField.setFont(fieldFont);
        mobileField.setForeground(Color.WHITE);
        mobileField.setBackground(Color.GRAY);


        emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);



        emailField = new JTextField(30);
        emailField.setFont(fieldFont);
        emailField.setForeground(Color.WHITE);
        emailField.setBackground(Color.GRAY);


        // Create the buttons
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setFont(labelFont);
        displayButton = new JButton("Display");
        displayButton.addActionListener(this);
        displayButton.setFont(labelFont);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setFont(labelFont);

        deleteCheckbox = new JCheckBox("Delete");
        deleteCheckbox.setBackground(Color.DARK_GRAY);
        deleteCheckbox.setForeground(Color.WHITE);
        deleteCheckbox.addActionListener(this);
        deleteCheckbox.setFont(labelFont);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        deleteButton.setFont(labelFont);
        deleteButton.setEnabled(false);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setFont(labelFont);
        searchButton.setBackground(Color.DARK_GRAY);
        searchButton.setForeground(Color.WHITE);


        // Set button background color
//        Color buttonColor = new Color(227, 227, 227); // light grey
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);

        displayButton.setBackground(Color.DARK_GRAY);
        displayButton.setForeground(Color.WHITE);

        clearButton.setBackground(Color.DARK_GRAY);
        clearButton.setForeground(Color.WHITE);

        deleteButton.setBackground(Color.DARK_GRAY);
        deleteButton.setForeground(Color.WHITE);


        // Create the panel and add the components
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(8, 2, 10, 10));
//        panel.add(titlel);
//        panel.add(titlen);

        panel.add(empid);
        panel.add(empidField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(mobileLabel);
        panel.add(mobileField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(saveButton);
        panel.add(displayButton);
//        panel.add(searchButton);
        panel.add(clearButton);
        panel.add(deleteCheckbox);
        panel.add(deleteButton);

        // Add the panel to the frame
        setContentPane(panel);
        setVisible(true);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Get number of columns
        int columnCount = metaData.getColumnCount();

        // Create a vector to hold column names
        Vector<String> columnNames = new Vector<String>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Create a vector to hold table data
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> row = new Vector<Object>();
            for (int column = 1; column <= columnCount; column++) {
                row.add(rs.getObject(column));
            }
            data.add(row);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == saveButton) {
            // Get the information from the text fields
            String empid = empidField.getText();
            String name = nameField.getText();
            String salary = salaryField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();

            // Insert the information into the database
            try {

                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO emp_info(empid, name,salary, mobile, email) VALUES (?, ?, ?, ?, ?)");
                stmt.setString(1, empid);
                stmt.setString(2, name);
                stmt.setString(3, salary);
                stmt.setString(4, mobile);
                stmt.setString(5, email);
                stmt.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(this, "Information saved successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error saving information: " + ex.getMessage());
            }
        }
        else if (e.getSource() == searchButton) {
// Get the search query from the user
            String query = JOptionPane.showInputDialog(this, "Enter search query:");
            // Search the database for matching records
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM emp_info WHERE empid LIKE ? OR name LIKE ? OR salary LIKE ? OR mobile LIKE ? OR email LIKE ?");
                for (int i = 1; i <= 5; i++) {
                    stmt.setString(i, "%" + query + "%");
                }
                ResultSet rs = stmt.executeQuery();
                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append("empid: ").append(rs.getString("empid")).append("\n")
                            .append("Name: ").append(rs.getString("name")).append("\n")
                            .append("salary: ").append(rs.getString("salary")).append("\n")
                            .append("Mobile: ").append(rs.getString("mobile")).append("\n")
                            .append("Email: ").append(rs.getString("email")).append("\n\n");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    JOptionPane.showMessageDialog(this, "No matching records found.");
                }
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error searching information: " + ex.getMessage());
            }

        }
        else if (e.getSource() == displayButton) {
            // Display the information from the database
            try {
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM emp_info");

                // Create a JTable and populate it with the data from the ResultSet
                JTable table = new JTable(buildTableModel(rs));

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);
//                scrollPane.setBackground(Color.DARK_GRAY);
//                scrollPane.setForeground(Color.DARK_GRAY);

                // Display the scroll pane in a dialog box
                JOptionPane.showMessageDialog(this, scrollPane);

                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error displaying information: " + ex.getMessage());
            }
        }

// Method to build a table model from a ResultSet

        else if (e.getSource() == clearButton) {
// Clear the text fields
            empidField.setText("");
            nameField.setText("");
            salaryField.setText("");
            mobileField.setText("");
            emailField.setText("");
        } else if (e.getSource() == deleteCheckbox) {
// Enable or disable the delete button based on the checkbox selection
            deleteButton.setEnabled(deleteCheckbox.isSelected());
        } else if (e.getSource() == deleteButton) {
// Delete the information from the database
            String empid = empidField.getText();
            if (!empid.equals("")) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this information?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
                        PreparedStatement stmt = conn.prepareStatement("DELETE FROM emp_info WHERE empid = ?");
                        stmt.setString(1, empid);
                        int result = stmt.executeUpdate();
                        conn.close();
                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Information deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "No information found with that empid.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Error deleting information: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a empid to delete.");
            }
        }
    }
}