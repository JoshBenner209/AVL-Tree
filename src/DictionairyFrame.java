/*
 * Josh Benner
 * CS 145 
 * March 14 2023
 * 
 * GUI for adding people to a data base. 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DictionairyFrame {
    private Dictionairy dictionairy;
    private DefaultTableModel model;
    private JTable table;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton ClearFieldButton;
    private JButton preOrderButton;
    private JButton postOrderButton;
    private JButton inOrderButton;


    public DictionairyFrame() {
      dictionairy= new Dictionairy();
        createUI();
    }

    /**
     * creates the User Interface
     */
    private void createUI() {
        // Create the main frame
        JFrame frame = new JFrame("DataBase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        // Create the table model and table
        model = new DefaultTableModel(new Object[]{"ID","First Name", "Last Name", "Email", "Phone", "Address", "City", "State", "Zip"}, 0);
        table = new JTable(model);
    
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
       
        // Create the input fields
        idField= new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        emailField = new JTextField(20);
        phoneField = new JTextField(10);
        addressField = new JTextField(20);
        cityField = new JTextField(10);
        stateField = new JTextField(2);
        zipField = new JTextField(5);
    
        //create add button 
        addButton = new JButton("Add");
        addButton.setToolTipText("Fill in all fields and click \"add\" to add Employee");
        //add button Action event listner 
        addButton.addActionListener( e -> {
            add();
            updateTableInorder();
            clearField();
        });
            
            // create a clear button that clears all inputs
        ClearFieldButton= new JButton("Clear");
        ClearFieldButton.setToolTipText("clear all fields");
       //clear action event listner 
        ClearFieldButton.addActionListener(e -> {
            firstNameField.setText(""); 
            lastNameField.setText("");
            idField.setText("");
            emailField.setText("");
            phoneField.setText("");
            addressField.setText("");
            stateField.setText("");
            cityField.setText("");
            zipField.setText("");
        });
        // create edit button 
        editButton = new JButton("Edit");
        editButton.setToolTipText("Must enter ID and Type changes into corresponding fields.");
        //edit button actoinListneer 
        editButton.addActionListener(e -> {
           edit();
           updateTableInorder();
        });
        
        // create delet button
        deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Enter Id to delet");
        //delet button Action event listner
        deleteButton.addActionListener(e -> {
            deleteEntry();
            updateTableInorder();
        });
        // create search button
        searchButton = new JButton("Search");
        searchButton.setToolTipText("enter Id to search");
        //search button action event listner 
        searchButton.addActionListener(e-> {
            searchEntries();
        });
        //create preOreder button
        preOrderButton = new JButton("Display PreOrder");
        //preOrder action event listner
        preOrderButton.addActionListener(e-> {
            updateTablePreorder();
        });  
        //post order button
        postOrderButton = new JButton("Display PostOrder");
        //post order action event listner
        postOrderButton.addActionListener(e-> {
            updateTablePostorder();
        });  
        // inOrder button
        inOrderButton = new JButton("Display InOrder");
        // inOrder action event listner
        inOrderButton.addActionListener(e-> {
            updateTableInorder();
        });  
        
        // Create the input and add buttons, fields and labels
        JPanel inputPanel = new JPanel();
        //fields and labels
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("First Name:"));
        inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last Name:"));
        inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(new JLabel("State:"));
        inputPanel.add(stateField);
        inputPanel.add(new JLabel("Zip:"));
        inputPanel.add(zipField);
        inputPanel.add(addButton);
        //buttons
        inputPanel.add(editButton);
        inputPanel.add(searchButton);
        inputPanel.add(deleteButton);
        inputPanel.add(ClearFieldButton);
        inputPanel.add(postOrderButton);
        inputPanel.add(preOrderButton);
        inputPanel.add(inOrderButton);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // create main panel 
        JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(inputPanel, BorderLayout.WEST);
            frame.setContentPane(mainPanel);
            frame.setSize(800, 600);
            frame.setVisible(true);
    }
    /**
     * search for person and display result in an option panel. 
     */
    private void searchEntries() {
        Integer id= Integer.valueOf(idField.getText());
        if (dictionairy.verify(id)==false){
            JOptionPane.showMessageDialog(null, "No results found for ID: "+id);
        } else{
        JOptionPane.showMessageDialog(null, dictionairy.search(id));
        }
    }   
    // set all fields to empty
    private void clearField(){
        firstNameField.setText(""); 
        lastNameField.setText("");
        idField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        stateField.setText("");
        cityField.setText("");
        zipField.setText("");
    }
    private void updateTableInorder() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesInOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }
    private void updateTablePreorder() {
        // Clear the table
        model.setRowCount(0);
    
        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesPreOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }
    private void updateTablePostorder() {
        // Clear the table
        model.setRowCount(0);

        // Add the updated entries to the table
        for (PersonInfo person : dictionairy.getEntriesPostOrder()) {
            Object[] row = new Object[]{person.getId(), person.getFirstName(), person.getLastName(),
                 person.getEmail(), person.getPhoneNumb(), person.getAddress(), person.getCity(), person.getState(), 
                 person.getZip()};
            model.addRow(row);
        }
    }

    // edit person. if field not empty replace it with text field
    private void edit(){
        Integer id = Integer.valueOf(idField.getText());
        PersonInfo person = dictionairy.getPerson(id);
        if (!firstNameField.getText().isEmpty()) {
            person.setFirstName(firstNameField.getText());
        }
        if (!lastNameField.getText().isEmpty()) {
            person.setLastName(lastNameField.getText());
        }
        if (!emailField.getText().isEmpty()) {
            person.setEmail(emailField.getText());
        }
        if (!phoneField.getText().isEmpty()) {
            person.setPhoneNumb(phoneField.getText());
        }
        if (!stateField.getText().isEmpty()) {
            person.setState(stateField.getText());
        }
        if (!addressField.getText().isEmpty()) {
            person.setAddress(addressField.getText());
        }
        if (!cityField.getText().isEmpty()) {
            person.setCity(cityField.getText());
        }
        if (!zipField.getText().isEmpty()) {
            person.setZip(zipField.getText());
        }
    
        // display a message to the user
        JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been updated.");
    }
    // get info and add new person
    private void add(){
            String fName= firstNameField.getText();
            String lName= lastNameField.getText();
            String id = idField.getText();
            Integer idInteger= Integer.valueOf(id);
            String email=emailField.getText();
            String phone=phoneField.getText();
            String address=addressField.getText();
            String state=stateField.getText();
            String city=cityField.getText();
            String zip=zipField.getText();
            dictionairy.addEntry(idInteger, fName, lName, email,phone,address,city,state,zip);
            
        //output.append(dictionairy.printInOrder());
        JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been added.");
    }
    // delet person 
    private void deleteEntry(){
        Integer id= Integer.valueOf(idField.getText());
        if(!idField.getText().isEmpty()){
            dictionairy.remove(id);
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " has been deleted");
        }else{
            JOptionPane.showMessageDialog(null, "Entry with ID " + id + " Not found.");
        }
    }
}
    