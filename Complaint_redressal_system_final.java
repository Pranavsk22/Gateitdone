import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;
import java.sql.*;

public class ComplaintRedressalSystem_final extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel, addComplaintPanel, searchComplaintPanel, closeComplaintPanel;
    
    public ComplaintRedressalSystem_final() {
        setTitle("Gated Community Complaint Redressal System");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Left side options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(3, 1, 10, 10)); // Line 17: Add spacing between buttons
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        optionsPanel.setBackground(new Color(70, 130, 180));  // Set a steel blue background color

        
        JButton btnAddComplaint = new JButton("Add New Complaint");
        btnAddComplaint.setBackground(new Color(72, 61, 139));  // Line 20: Dark Slate Blue
        btnAddComplaint.setForeground(Color.WHITE);
        btnAddComplaint.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font

        JButton btnSearchComplaint = new JButton("Search Complaint");
        btnSearchComplaint.setBackground(new Color(72, 61, 139));  // Dark Slate Blue
        btnSearchComplaint.setForeground(Color.WHITE);
        btnSearchComplaint.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font

        JButton btnCloseComplaint = new JButton("Close Complaint");    
        btnCloseComplaint.setBackground(new Color(72, 61, 139));  // Dark Slate Blue
        btnCloseComplaint.setForeground(Color.WHITE);
        btnCloseComplaint.setFont(new Font("Arial", Font.BOLD, 14)); // Adjust font

        
        optionsPanel.add(btnAddComplaint);
        optionsPanel.add(btnSearchComplaint);
        optionsPanel.add(btnCloseComplaint);
        //adding buttons in the options panel
        
        // Main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add Complaint Panel
        addComplaintPanel = new JPanel();
        addComplaintPanel.setBackground(new Color(245, 245, 245));
        addComplaintPanel.setLayout(new GridLayout(8, 2, 10, 20));  // Adjusted to accommodate new fields

        addComplaintPanel.add(new JLabel("Name:"));
        JTextField txtName = new JTextField();
        addComplaintPanel.add(txtName);

        addComplaintPanel.add(new JLabel("Apartment Number:"));
        JTextField txtApartmentNumber = new JTextField();
        addComplaintPanel.add(txtApartmentNumber);

        addComplaintPanel.add(new JLabel("Complaint Category:"));
        String[] categories = {"Electrical", "Plumbing", "Civil"};
        JComboBox<String> cmbCategory = new JComboBox<>(categories);
        addComplaintPanel.add(cmbCategory);

        addComplaintPanel.add(new JLabel("Description:"));
        JTextField txtDescription = new JTextField();
        addComplaintPanel.add(txtDescription);

        addComplaintPanel.add(new JLabel("Phone Number:"));
        JTextField txtPhoneNumber = new JTextField();
        addComplaintPanel.add(txtPhoneNumber);

        // Add new fields for service executive name and number
        addComplaintPanel.add(new JLabel("Service Exec Name:"));
        JTextField txtServiceExecName = new JTextField();
        txtServiceExecName.setEditable(false);  // Non-editable
        addComplaintPanel.add(txtServiceExecName);

        addComplaintPanel.add(new JLabel("Service Exec Number:"));
        JTextField txtServiceExecNumber = new JTextField();
        txtServiceExecNumber.setEditable(false);  // Non-editable
        addComplaintPanel.add(txtServiceExecNumber);

        JButton btnSubmit = new JButton("Submit");
        addComplaintPanel.add(btnSubmit);

        PlainDocument docApartment = (PlainDocument) txtApartmentNumber.getDocument();
        docApartment.setDocumentFilter(new NumericDocumentFilter());

        PlainDocument docPhone = (PlainDocument) txtPhoneNumber.getDocument();
        docPhone.setDocumentFilter(new NumericDocumentFilter());

        // Add ActionListener to handle category selection and auto-fill service exec details
        cmbCategory.addActionListener(e -> {
            String selectedCategory = (String) cmbCategory.getSelectedItem();
            if ("Electrical".equals(selectedCategory)) {
                txtServiceExecName.setText("P. Rajaram");
                txtServiceExecNumber.setText("893471235");
            } else if ("Plumbing".equals(selectedCategory)) {
                txtServiceExecName.setText("Leo Das");
                txtServiceExecNumber.setText("38476948573");
            } else if ("Civil".equals(selectedCategory)) {
                txtServiceExecName.setText("Guna");
                txtServiceExecNumber.setText("4756381637");
            }
        });

        
        // Search Complaint Panel
        searchComplaintPanel = new JPanel();
        searchComplaintPanel.setBackground(new Color(245, 245, 245));
        searchComplaintPanel.setLayout(new GridLayout(8, 2, 10, 20));  // Adjusted to accommodate new fields

        searchComplaintPanel.add(new JLabel("Name:"));
        JTextField txtSearchName = new JTextField();
        txtSearchName.setEditable(false);  // Non-editable
        searchComplaintPanel.add(txtSearchName);

        searchComplaintPanel.add(new JLabel("Apartment Number:"));
        JTextField txtSearchApartmentNumber = new JTextField();
        searchComplaintPanel.add(txtSearchApartmentNumber);  // Editable (as requested)

        searchComplaintPanel.add(new JLabel("Complaint Category:"));
        String[] Scategories = {"Electrical", "Plumbing", "Civil"};
        JComboBox<String> cmbSearchCategory = new JComboBox<>(Scategories);
        cmbSearchCategory.setEnabled(false);  // Non-editable (disabled)
        searchComplaintPanel.add(cmbSearchCategory);

        searchComplaintPanel.add(new JLabel("Description:"));
        JTextField txtSearchDescription = new JTextField();
        txtSearchDescription.setEditable(false);  // Non-editable
        searchComplaintPanel.add(txtSearchDescription);

        searchComplaintPanel.add(new JLabel("Phone Number:"));
        JTextField txtSearchPhoneNumber = new JTextField();
        txtSearchPhoneNumber.setEditable(false);  // Non-editable
        searchComplaintPanel.add(txtSearchPhoneNumber);

        // Add new fields for service executive name and number
        searchComplaintPanel.add(new JLabel("Service Exec Name:"));
        JTextField txtSearchServiceExecName = new JTextField();
        txtSearchServiceExecName.setEditable(false);  // Non-editable
        searchComplaintPanel.add(txtSearchServiceExecName);

        searchComplaintPanel.add(new JLabel("Service Exec Number:"));
        JTextField txtSearchServiceExecNumber = new JTextField();
        txtSearchServiceExecNumber.setEditable(false);  // Non-editable
        searchComplaintPanel.add(txtSearchServiceExecNumber);

        JButton btnSearch = new JButton("Search");
        searchComplaintPanel.add(btnSearch);

        // Add ActionListener for category selection to auto-fill service exec details (if applicable)
        cmbSearchCategory.addActionListener(e -> {
            String selectedCategory = (String) cmbSearchCategory.getSelectedItem();
            if ("Electrical".equals(selectedCategory)) {
                txtSearchServiceExecName.setText("P. Rajaram");
                txtSearchServiceExecNumber.setText("893471235");
            } else if ("Plumbing".equals(selectedCategory)) {
                txtSearchServiceExecName.setText("Leo Das");
                txtSearchServiceExecNumber.setText("38476948573");
            } else if ("Civil".equals(selectedCategory)) {
                txtSearchServiceExecName.setText("Guna");
                txtSearchServiceExecNumber.setText("4756381637");
            }
        });

        // Close Complaint Panel
        closeComplaintPanel = new JPanel();
        closeComplaintPanel.setBackground(new Color(245, 245, 245));
        closeComplaintPanel.setLayout(new GridLayout(8, 2, 10, 20));  // Adjusted to accommodate new fields

        closeComplaintPanel.add(new JLabel("Name:"));
        JTextField txtCloseName = new JTextField();
        txtCloseName.setEditable(false);  // Non-editable
        closeComplaintPanel.add(txtCloseName);

        closeComplaintPanel.add(new JLabel("Apartment Number:"));
        JTextField txtCloseApartmentNumber = new JTextField();
        closeComplaintPanel.add(txtCloseApartmentNumber);  // Editable (as requested)

        closeComplaintPanel.add(new JLabel("Complaint Category:"));
        String[] Ccategories = {"Electrical", "Plumbing", "Civil"};
        JComboBox<String> cmbCloseCategory = new JComboBox<>(Ccategories);
        cmbCloseCategory.setEnabled(false);  // Non-editable (disabled)
        closeComplaintPanel.add(cmbCloseCategory);

        closeComplaintPanel.add(new JLabel("Description:"));
        JTextField txtCloseDescription = new JTextField();
        txtCloseDescription.setEditable(false);  // Non-editable
        closeComplaintPanel.add(txtCloseDescription);

        closeComplaintPanel.add(new JLabel("Phone Number:"));
        JTextField txtClosePhoneNumber = new JTextField();
        txtClosePhoneNumber.setEditable(false);  // Non-editable
        closeComplaintPanel.add(txtClosePhoneNumber);

        // Add new fields for service executive name and number
        closeComplaintPanel.add(new JLabel("Service Exec Name:"));
        JTextField txtCloseServiceExecName = new JTextField();
        txtCloseServiceExecName.setEditable(false);  // Non-editable
        closeComplaintPanel.add(txtCloseServiceExecName);

        closeComplaintPanel.add(new JLabel("Service Exec Number:"));
        JTextField txtCloseServiceExecNumber = new JTextField();
        txtCloseServiceExecNumber.setEditable(false);  // Non-editable
        closeComplaintPanel.add(txtCloseServiceExecNumber);

        JButton btnClose = new JButton("Close Complaint");
        closeComplaintPanel.add(btnClose);

        JButton btnSearchtoClose = new JButton("Search");
        closeComplaintPanel.add(btnSearchtoClose);

        // Add ActionListener for category selection to auto-fill service exec details (if applicable)
        cmbCloseCategory.addActionListener(e -> {
            String selectedCategory = (String) cmbCloseCategory.getSelectedItem();
            if ("Electrical".equals(selectedCategory)) {
                txtCloseServiceExecName.setText("P. Rajaram");
                txtCloseServiceExecNumber.setText("893471235");
            } else if ("Plumbing".equals(selectedCategory)) {
                txtCloseServiceExecName.setText("Leo Das");
                txtCloseServiceExecNumber.setText("38476948573");
            } else if ("Civil".equals(selectedCategory)) {
                txtCloseServiceExecName.setText("Guna");
                txtCloseServiceExecNumber.setText("4756381637");
            }
        });

        
        // Adding panels to main panel
        mainPanel.add(addComplaintPanel, "AddComplaintPanel");
        mainPanel.add(searchComplaintPanel, "SearchComplaintPanel");
        mainPanel.add(closeComplaintPanel, "CloseComplaintPanel");
        
        // Add action listeners
        btnAddComplaint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "AddComplaintPanel");
            }
        });
        
        btnSearchComplaint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SearchComplaintPanel");
            }
        });

        btnCloseComplaint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "CloseComplaintPanel");
            }
        });

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14)); // Set JOptionPane font globally
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 14));

        btnSearchtoClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apartmentNumber = txtCloseApartmentNumber.getText(); // Changed to txtSearchApartmentNumber
                
                // Check if the apartment number field is not empty
                if (apartmentNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an Apartment Number.");
                    return;
                }
        
                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
        
                    // Establish the connection
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Complaintdb", "<username>", "<password>");
        
                    // Prepare the SQL query
                    String query = "SELECT * FROM Complaint WHERE Apartmentno = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, apartmentNumber);
        
                    // Execute the query
                    ResultSet rs = pstmt.executeQuery();
        
                    // Check if a record was found
                    if (rs.next()) {
                        // Populate the search panel form fields with the data from the database
                        txtSearchName.setText(rs.getString("Name"));  // Column name must match database
                        cmbSearchCategory.setSelectedItem(rs.getString("Complaintcat"));
                        txtSearchPhoneNumber.setText(rs.getString("Phone"));
                        txtSearchDescription.setText(rs.getString("Description"));

                        txtSearchServiceExecName.setText(rs.getString("ServiceExecName"));
                        txtSearchServiceExecNumber.setText(rs.getString("ServiceExecPhone"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No Complaint found for Apartment Number: " + apartmentNumber);
                    }
        
                    // Close the connection
                    rs.close();
                    pstmt.close();
                    con.close();
                } catch (ClassNotFoundException cnfe) {
                    JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found. Please add the driver to your project.");
                    cnfe.printStackTrace();
                } catch (SQLException sqle) {
                    JOptionPane.showMessageDialog(null, "Database error: " + sqle.getMessage());
                    sqle.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String apartmentNumber = txtApartmentNumber.getText();
                String category = (String) cmbCategory.getSelectedItem();
                String phoneNumber = txtPhoneNumber.getText();
                String description = txtDescription.getText();

                String ServiceExecName = txtServiceExecName.getText();
                String ServiceExecPhone = txtServiceExecNumber.getText();
                // Handle the form submission (e.g., save to database)
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Complaintdb", "<user>", "<password>");
        
                    String query = "INSERT INTO Complaint (Name, Apartmentno, Complaintcat, Phone, Description, ServiceExecName,ServiceExecPhone,Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, apartmentNumber);
                    pstmt.setString(3, category);
                    pstmt.setString(4, phoneNumber);
                    pstmt.setString(5, description);

                    pstmt.setString(6, ServiceExecName);
                    pstmt.setString(7, ServiceExecPhone);
                    pstmt.setString(8, "OPEN");
                    
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Complaint submitted successfully!");
                    }
                    
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
        
                // Clear form fields
                txtName.setText("");
                txtApartmentNumber.setText("");
                txtPhoneNumber.setText("");
                txtDescription.setText("");

                txtServiceExecName.setText("");
                txtServiceExecNumber.setText("");
            }
        });
        
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apartmentNumber = txtSearchApartmentNumber.getText(); // Changed to txtSearchApartmentNumber
                // Check if the apartment number field is not empty
                if (apartmentNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an Apartment Number.");
                    return;
                }
                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Establish the connection
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Complaintdb", "root", "pranav22");
                    // Prepare the SQL query
                    String query = "SELECT * FROM Complaint WHERE Apartmentno = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, apartmentNumber);
                    // Execute the query
                    ResultSet rs = pstmt.executeQuery();
                    // Check if a record was found
                    if (rs.next()) {
                        // Populate the search panel form fields with the data from the database
                        txtSearchName.setText(rs.getString("Name"));  // Column name must match database
                        cmbSearchCategory.setSelectedItem(rs.getString("Complaintcat"));
                        txtSearchPhoneNumber.setText(rs.getString("Phone"));
                        txtSearchDescription.setText(rs.getString("Description"));

                        txtSearchServiceExecName.setText(rs.getString("ServiceExecName"));
                        txtSearchServiceExecNumber.setText(rs.getString("ServiceExecPhone"));
                    } else {
                        JOptionPane.showMessageDialog(null, "No complaint found for Apartment Number: " + apartmentNumber);
                    }
        
                    // Close the connection
                    rs.close();
                    pstmt.close();
                    con.close();
                } catch (ClassNotFoundException cnfe) {
                    JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found. Please add the driver to your project.");
                    cnfe.printStackTrace();
                } catch (SQLException sqle) {
                    JOptionPane.showMessageDialog(null, "Database error: " + sqle.getMessage());
                    sqle.printStackTrace();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });        
        



        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apartmentNumber = txtCloseApartmentNumber.getText(); // Changed to txtSearchApartmentNumber
                
                // Check if the apartment number field is not empty
                if (apartmentNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an Apartment Number.");
                    return;
                }
        
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");  // Load MySQL JDBC Driver
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Complaintdb", "<user>", "<password>");
        
                    String query = "UPDATE Complaint SET Status='CLOSED' WHERE Apartmentno=?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, apartmentNumber);                    
                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Complaint closed successfully!");
                    }
                    
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        
        // Layout configuration
        setLayout(new BorderLayout());
        add(optionsPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        
        // Start with the Add Complaint Panel
        cardLayout.show(mainPanel, "AddComplaintPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ComplaintRedressalSystem_final().setVisible(true);
            }
        });
    }
}
