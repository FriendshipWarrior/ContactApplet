package main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import validation.Validation;

import models.BusinessRelationship;
import models.Contacts;
import models.FamilyRelationship;
import models.FriendRelationship;
import models.Person;
import models.Relationship;


public class GUI extends JFrame {

	private JPanel contentPane;
	private ButtonGroup relationship_group = new ButtonGroup();
	private JTextField first_name;
	private JTextField last_name;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField phone;
	private JTextField email;
	
	private String fileName = "/Users/the_friendship_warrior/contacts.ser";
	private Contacts contacts;
	private FieldListener fl = new FieldListener(this);
	private boolean failed_validation = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new MyWindowListener(this));
		setBounds(100, 100, 757, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contacts = new Contacts();
		
		// automatically load contacts
		loadContacts();

		JButton btnSave = new JButton("Add / Update Contact");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Person p = new Person();
				// set all the properties of a person using all the input fields
				p.setFirstName(first_name.getText());
				
				// set the rest of the person fields (person is non validated)
				boolean isPersonValid = validatePerson(p);
				
				// add person to contacts; only do it validation is true
				if (isPersonValid) {
					// figure out state of the radio button
					String action_command = relationship_group.getSelection().getActionCommand();
					Relationship r = getRelationshipFromActionCommand(action_command);
					
					// add the person w/ relationship to contacts
					contacts.addPerson(p, r);
				}
				else {
					showWarningPopup("Contact Validation", "Failed Validation - Not Saving!", JOptionPane.OK_OPTION);
				}
			}
		});
		btnSave.setBounds(189, 362, 176, 29);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Remove Contact");
		btnDelete.setBounds(394, 362, 172, 29);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 22, 87, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(162, 22, 90, 16);
		contentPane.add(lblLastName);
		
		first_name = new JTextField();
		first_name.setBounds(10, 34, 134, 28);
		contentPane.add(first_name);
		first_name.setColumns(10);
		first_name.setName("FirstName");
		first_name.addFocusListener(fl);

		
		last_name = new JTextField();
		last_name.setBounds(162, 34, 134, 28);
		contentPane.add(last_name);
		last_name.setColumns(10);
		last_name.setName("LastName");
		last_name.addFocusListener(fl);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 62, 61, 16);
		contentPane.add(lblAddress);
		
		address = new JTextField();
		address.setBounds(10, 74, 134, 28);
		contentPane.add(address);
		address.setColumns(10);
		address.setName("Address");
		address.addFocusListener(fl);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(162, 62, 61, 16);
		contentPane.add(lblCity);
		
		city = new JTextField();
		city.setBounds(162, 74, 134, 28);
		contentPane.add(city);
		city.setColumns(10);
		city.setName("City");
		city.addFocusListener(fl);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(10, 100, 61, 16);
		contentPane.add(lblState);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(162, 100, 61, 16);
		contentPane.add(lblZip);
		
		JLabel lblPhonexxxxxxxxxx = new JLabel("Phone (xxx-xxx-xxxx)");
		lblPhonexxxxxxxxxx.setBounds(10, 143, 146, 16);
		contentPane.add(lblPhonexxxxxxxxxx);
		
		JLabel lblEmailnameexamplecom = new JLabel("Email (name@example.com)");
		lblEmailnameexamplecom.setBounds(162, 143, 194, 16);
		contentPane.add(lblEmailnameexamplecom);
		
		state = new JTextField();
		state.setBounds(10, 114, 134, 28);
		contentPane.add(state);
		state.setColumns(10);
		state.setName("State");
		state.addFocusListener(fl);
		
		zip = new JTextField();
		zip.setBounds(162, 114, 134, 28);
		contentPane.add(zip);
		zip.setColumns(10);
		zip.setName("Zip");
		zip.addFocusListener(fl);
		
		phone = new JTextField();
		phone.setBounds(10, 154, 134, 28);
		contentPane.add(phone);
		phone.setColumns(10);
		phone.setName("Phone");
		phone.addFocusListener(fl);
		
		email = new JTextField();
		email.setBounds(162, 154, 134, 28);
		contentPane.add(email);
		email.setColumns(10);
		email.setName("Email");
		email.addFocusListener(fl);
		
		JComboBox contact_display = new JComboBox();
		contact_display.setBounds(456, 39, 209, 65);
		contentPane.add(contact_display);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(52, 328, 650, 22);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(356, 34, 9, 277);
		contentPane.add(separator_1);
		
		/* Radio Buttons */
		JRadioButton rdbtnFriends = new JRadioButton("Friends");
		rdbtnFriends.setSelected(true);
		rdbtnFriends.setBounds(443, 143, 172, 16);
		rdbtnFriends.setActionCommand("Friend");
		contentPane.add(rdbtnFriends);
		
		JRadioButton rdbtnFamily = new JRadioButton("Family");
		rdbtnFamily.setBounds(443, 171, 172, 16);
		rdbtnFamily.setActionCommand("Family");
		contentPane.add(rdbtnFamily);
		
		JRadioButton rdbtnWork = new JRadioButton("Work");
		rdbtnWork.setActionCommand("Work");
		rdbtnWork.setBounds(443, 199, 172, 16);
		contentPane.add(rdbtnWork);
		
		relationship_group.add(rdbtnFriends);
		relationship_group.add(rdbtnFamily);
		relationship_group.add(rdbtnWork);
		
		/* Save and Load Buttons */
		JButton btnSave_1 = new JButton("Save Contacts");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saveContacts();
			}
		});
		btnSave_1.setBounds(219, 403, 146, 29);
		contentPane.add(btnSave_1);
		
		JButton btnLoadContacts = new JButton("Load Contacts");
		btnLoadContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				loadContacts();
			}
		});
		btnLoadContacts.setBounds(394, 403, 146, 29);
		contentPane.add(btnLoadContacts);
		
	}
	
	private boolean validatePerson(Person p) {
		// for each relevant field of person, call
		// validation logic, (and if you want - track which ones fail)
		
		//boolean firstPasses = Validation.validateFirstName(p.getFirstName());
		//boolean lastPasses = Validation.validateLastName(p.getLastName());
		
		// for now; assume validation passes
		// but you could, send the list of failures
		return true;
	}
	
	public void saveContacts()
	{
		try
		{
			FileOutputStream fileOs = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fileOs);
			os.writeObject(contacts);
			os.flush();
			os.close();
		}
		catch (Throwable t)
		{
			System.out.println("Error saving file");
			t.printStackTrace();
		}
		
	}
	
	public void loadContacts()
	{
		try
		{
			FileInputStream fileIs = new FileInputStream(fileName);
			ObjectInputStream is = new ObjectInputStream(fileIs);
			contacts = (Contacts) is.readObject();
			is.close();
		}
		catch (Throwable t)
		{
			System.out.println("Error loading file");
			t.printStackTrace();
		}
		System.out.println("Size of Contacts: " + contacts.length());
	}
	
	private Relationship getRelationshipFromActionCommand(String command) {
		if (command.equalsIgnoreCase("Work")) {
			return new BusinessRelationship();
		}
		else if (command.equalsIgnoreCase("Friend")) {
			return new FriendRelationship();
		}
		else {
			return new FamilyRelationship();
		}
	}
	
	/**
	 * Displays a standard warning message which can be used to
	 * alert the user of problems or critical messages.
	 */
	private void showWarningPopup(String title, Object msg, int type) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(this, msg, title, type);			
	}
	
}

class MyWindowListener implements WindowListener {
	private GUI gui;
	MyWindowListener(GUI gui) {
		super();
		this.gui = gui;
	}
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {
		System.out.println("Closed Window!");
	}
	public void windowClosing(WindowEvent e) {
		System.out.println("Closing Window!");
		gui.saveContacts();
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}	
}

class FieldListener implements FocusListener {
	
	private GUI gui;
	
	FieldListener(GUI gui) {
		super();
		this.gui = gui;
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*
		JTextField source = ((JTextField)e.getSource());
		String field_name = source.getName();
		String field_value =source.getText();
		
		// FirstName, LastName, Address, City, State, Zip, Phone, Email
		
		boolean has_problem = false;
		if (field_name.equalsIgnoreCase("FirstName")) {
			has_problem = !Validation.validateFirstName(field_value);
		}
		else if (field_name.equalsIgnoreCase("LastName")) {
			has_problem = !Validation.validateLastName(field_value);
		}
		// ... repeat for all 8 fields
		
		if (has_problem) {
			this.showWarningPopup("Field Validation Failure", field_name + "::" + field_value, JOptionPane.OK_OPTION);
		}
		*/
	}
	
	/**
	 * Displays a standard warning message which can be used to
	 * alert the user of problems or critical messages.
	 */
	private void showWarningPopup(String title, Object msg, int type) {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(gui, msg, title, type);			
	}

}