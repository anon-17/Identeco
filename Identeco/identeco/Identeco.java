package identeco;

/* 
 * Title: Identeco
 * License or copyright: N/A
 * Version: 1.0
 * Date of Last Update: 4/27/2015
 * Developer: Malcolm Royer
 * 
 * This application is designed to provide a means of managing and randomly accessing numerous
 * alternate identities. It enables creating, editing, deleting, and displaying data stored in
 * Identity objects. The list of identities is read from and saved to a text document co-located 
 * with the class files and titled "identityList.txt".
 * 
 * Future additions:
 *   1. read from and save to XML instead of text file
 *   2. ability to generate a fake phone number with an area code that matches address
 *   3. ability to generate real address using Zillow
 *   4. ability to generate name based off of most common first, middle, and last names
 *   5. ability to generate password based off of 1337 version of username
 *   6. ability to generate address
 *   7. ability to generate email
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial") // I do not intend to serialize this application at this time
public class Identeco extends JFrame implements ActionListener {
	
	private JPanel panelTitle, panelOne, panelTwo, panelThree, panelFour, panelFive, panelSix, 
		panelSeven, panelEight, panelNine, panelTen, panelEleven, panelTwelve, panelButtons1, 
		panelButtons2, panelTextArea;
	private JLabel labelIdNumber, labelUsername, labelTimesUsed, labelFirstName, 
		labelLastName, labelMiddleInitial, labelGender, labelBirthdate, labelPhoneNumber, 
		labelEmail, labelPassword, labelAddress;
	private JTextField textFieldIdNumber, textFieldUsername, textFieldTimesUsed, 
		textFieldFirstName, textFieldLastName, textFieldMiddleInitial, textFieldGender, 
		textFieldBirthdate, textFieldPhoneNumber, textFieldEmail, textFieldPassword, 
		textFieldAddress;
	private JButton buttonNew, buttonDelete, buttonRandom, buttonDisplayAll, buttonEdit, buttonSave;
	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	private int numberOfIdentities;
	private Identity[] identityArray;
	private ArrayList<Identity> identityList;
	
	//Constructor
	public Identeco() {

		numberOfIdentities = 0;
		identityList = null;

		// Set up the GUI
		setUpGui();

	}//end constructor
	
	//Main
	public static void main(String[] args) throws IOException {

		// Set up the GUI by invoking the constructor
		Identeco instance = new Identeco();
		
		// Read from file
		instance.readFromFile();		

	}//end main

	private void readFromFile() throws IOException {
		
	    // If record file does not exist, create it and call it "identityList.txt"
		File file = new File("./", "identityList.txt");

		// If there is a problem, throw an IO Exception
		if (!file.isFile() && !file.createNewFile()) {
	
	    	throw new IOException("Error creating new file: " + file.getAbsolutePath());

	    }//end if

		// Create a Buffered Reader to read in lines from the file
	    BufferedReader r = new BufferedReader(new FileReader(file));

	    try {
	    
	    	String line = null; // start "line" string as empty
	    	int counter = 0;
	    	
	    	/* Find out how many saved identities there are 
	    	 * (first line of file contains total number) */
	    	line = r.readLine();
	    	numberOfIdentities = Integer.parseInt(line);
	    	
	    	// Make the array of identities big enough to hold all identities read in from file
	    	identityArray = new Identity[Integer.parseInt(line)];
	    	identityList = new ArrayList<Identity>();
	    	
	    	// Read from file
	    	// As long as there is another line in the file...
	    	while ((line = r.readLine()) != null) {
	    		
	    		Identity identityOnFile = new Identity(); // create a new identity to hold data

	    		// Read data from file into new identity
	    		identityOnFile.setIdNumber(line);
	    		identityOnFile.setTimesUsed(line = r.readLine());
	    		identityOnFile.setUsername(line = r.readLine());
	    		identityOnFile.setPassword(line = r.readLine());
	    		identityOnFile.setFirstName(line = r.readLine());
	    		identityOnFile.setMiddleInitial((line = r.readLine()));
	    		identityOnFile.setLastName(line = r.readLine());
	    		identityOnFile.setGender((line = r.readLine()));
	    		identityOnFile.setBirthDate(line = r.readLine());
	    		identityOnFile.setPhoneNumber(line = r.readLine());
	    		identityOnFile.setEmail(line = r.readLine());
	    		identityOnFile.setAddress(line = r.readLine());
	    		
	    		identityArray[counter] = identityOnFile; // add identity to "identityList" array

    			identityList.add(identityOnFile); // add identity to "identityList2" linked list

	    		counter++;

	    	}//end while
	    	
	    }//end try
	    finally {
	    		r.close();
	    }//end finally
	    
	}//end readFromFile
	
	private void writeToFile() throws IOException {
		
		try {
		
			// Overwrite the file "identityList.txt"
			File file = new File("./", "identityList.txt");

			// If file does not exist, create it
			if (!file.exists()) {

				file.createNewFile();
			
			}//end if
	
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			// Write the first line containing total number of identities to file
			bufferedWriter.write(Integer.toString(numberOfIdentities));
			
			
	    	// For the number of identities...
	    	for (int i=0; i< numberOfIdentities; i++) {
	    		
	    		// Write data from identity to file
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getIdNumber());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getTimesUsed());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getUsername());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getPassword());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getFirstName());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getMiddleInitial());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getLastName());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getGender());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getBirthDate());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getPhoneNumber());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getEmail());
	    		bufferedWriter.newLine();
	    		bufferedWriter.write(identityList.get(i).getAddress());
	    	  		
	    	}//end while			
			
	    	// Close the file
			bufferedWriter.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}//end try-catch
		
	}//end writeToFile
	
	// ActionEvent handler - Called back upon button-click.
	@Override   
	public void actionPerformed(ActionEvent evt) {
	      
		// If the New button is pressed...
		if (evt.getSource().equals(buttonNew)) {
			
			clearFields(); // clear all text fields
			editIdentity(); // call editIdentity method
			
		}//end if New button		
		// If the Random button is pressed...
		else if(evt.getSource().equals(buttonRandom)) {
			
			randomIdentity(); // call randomIdentity method
			
		}//end if Random button
		// If the Delete button is pressed...
		else if(evt.getSource().equals(buttonDelete)) {
			
			deleteIdentity(); // call deleteIdentity method
			
		}//end else if Delete button
		// If the Display All button is pressed...
		else if(evt.getSource().equals(buttonDisplayAll)) {
			
			displayAll(); // call displayAll method
			
		}//end else if Display All button
		// If the Edit button is pressed...
		else if(evt.getSource().equals(buttonEdit)) {
			
			editIdentity(); // call editIdentity method
			
		}//end else if Edit button
		// If the Save button is pressed...
		else if(evt.getSource().equals(buttonSave)) {
			
			try {

				saveIdentity(identityArray); // call saveIdentity method
			
			} catch (IOException e) {
			
				e.printStackTrace();
				
			}//end try-catch block
			
		}//end else if Save button
			   
	}//end actionPerformed
	
	// Display a random identity from the list into the fields

	private void randomIdentity() {

		int identityNumber = 0;
		Random rnd = new Random(); // create the random number generator
		
		// If there is more than one identity
		if(numberOfIdentities > 0) {

			//Select a random number from the list of identities and seed the random generator
			identityNumber = rnd.nextInt(numberOfIdentities); 
			
		    // For that identity, read its information and display in appropriate fields
			displayIdentity(identityArray[identityNumber]);

		    // Update times this identity has been used by +1
			identityArray[identityNumber].updateTimesUsed();
			
			// Make the Edit and Delete buttons usable
			buttonEdit.setEnabled(true);
			buttonDelete.setEnabled(true);
			
		}//end if

		else {
			// Do nothing because there are 0 identities right now
		}//end else
		
	};//end randomIdentity

	// Edit current identity in fields
	private void editIdentity() {
		
		// Set all fields to be able to edit text
		textFieldUsername.setEditable(true);
		textFieldPassword.setEditable(true);
		textFieldFirstName.setEditable(true);
		textFieldMiddleInitial.setEditable(true);
		textFieldLastName.setEditable(true);
		textFieldGender.setEditable(true);
		textFieldBirthdate.setEditable(true);
		textFieldPhoneNumber.setEditable(true);
		textFieldEmail.setEditable(true);
		textFieldAddress.setEditable(true); 
		
		// Disable New, Random, DisplayAll, and Edit buttons
		buttonNew.setEnabled(false);
		buttonRandom.setEnabled(false);
		buttonDisplayAll.setEnabled(false);
		buttonEdit.setEnabled(false);
		
		// Enable Save and Delete buttons
		buttonSave.setEnabled(true);
		buttonDelete.setEnabled(true);
				
	}//end saveIdentity
	
	// Saves current identity in fields to associated identity in list
	
	private void saveIdentity(Identity[] identities) throws IOException {
		
		int theRightIdentity = 0; // variable to act as placeholder in array
		
		// Find the identity in the array with the ID Number matching the one currently displayed
		for(int i=0; i<numberOfIdentities; i++) {
			
			if((identities[i].getIdNumber()).equals(textFieldIdNumber.getText())) {
				
				theRightIdentity = i;
				
			}//end if
		
		}//end for
	
		// Save text from fields to identity values
		identities[theRightIdentity].setUsername(textFieldUsername.getText());
		identities[theRightIdentity].setPassword(textFieldPassword.getText());
		identities[theRightIdentity].setFirstName(textFieldFirstName.getText());
		identities[theRightIdentity].setMiddleInitial(textFieldMiddleInitial.getText());
		identities[theRightIdentity].setLastName(textFieldLastName.getText());
		identities[theRightIdentity].setGender(textFieldGender.getText());
		identities[theRightIdentity].setBirthDate(textFieldBirthdate.getText());
		identities[theRightIdentity].setPhoneNumber(textFieldPhoneNumber.getText());
		identities[theRightIdentity].setEmail(textFieldEmail.getText());
		identities[theRightIdentity].setAddress(textFieldAddress.getText());
		
		// If adding a new identity, assign a new ID Number and set Times Used to 0
		if(identities[theRightIdentity].getIdNumber() == "0") {
			
			identities[theRightIdentity].setIdNumber(Integer.toString(numberOfIdentities + 1));
		
			numberOfIdentities++; // increase the number of identities counter by +1
			System.out.println(identities[theRightIdentity].getIdNumber());
			identities[theRightIdentity].setTimesUsed("0");
		
		}//end if newIdentity
		
		// Save the whole list of identities to a file
		writeToFile();
		
		// Clear and disable editing in all text fields
		clearFields();
		disableFields();
		
		// Disable Save, Delete, and Edit buttons
		buttonSave.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonEdit.setEnabled(false);
		
		// Re-enable New, Random, and DisplayAll buttons
		buttonNew.setEnabled(true);
		buttonRandom.setEnabled(true);
		buttonDisplayAll.setEnabled(true);
		
	}//end saveIdentity
	
	// Create a new identity, assigning the values to the arguments passed in
	
	public Identity createIdentity(int idNumbers, String username, String firstName, String lastName, 
			String middleInitial, String gender, String birthdate, String phoneNumber, String email, 
			String password, String address) {
	    
		//Create new object of type Identity and call it "newIdentity" 
		Identity newIdentity = new Identity();
	    
		//Set the variables in the object to what was passed into this method
	    newIdentity.setIdNumber(Integer.toString(idNumbers+1));
	    newIdentity.setTimesUsed("0");
		newIdentity.setUsername(username);
	    newIdentity.setPassword(password);
	    newIdentity.setFirstName(firstName);
	    newIdentity.setMiddleInitial(middleInitial);
	    newIdentity.setLastName(lastName);
	    newIdentity.setGender(gender);
	    newIdentity.setBirthDate(birthdate);
	    newIdentity.setPhoneNumber(phoneNumber);
	    newIdentity.setEmail(email);
	    newIdentity.setAddress(address);
	    
	    return newIdentity;
	    
	};//end createIdentity

	// Displays all identities
	@SuppressWarnings("static-access") // suppress static warnings for UIManager dialogBoxGui
	private void displayAll() {
	    
		String printOut = ""; // create string to hold output
		JOptionPane dialogBox = new JOptionPane(); // create dialog box / pop up window
		JTextArea dialogBoxTextArea = new JTextArea("",14,26); // construct text area
		JScrollPane dialogBoxScrollPane = new JScrollPane(dialogBoxTextArea); // add a scrollbar
		UIManager dialogBoxGui = new UIManager(); // used to change the look and feel of dialog box
		
		// Add output to the string
		for(int i=0; i<numberOfIdentities; i++) {
			
			printOut += "ID Number: " + identityList.get(i).getIdNumber() + "\n";
			printOut += "Times Used: " + identityList.get(i).getTimesUsed() + "\n";
			printOut += "Username: " + identityList.get(i).getUsername() + "\n";
			printOut += "Password: " + identityList.get(i).getPassword() + "\n";
			printOut += "First Name: " + identityList.get(i).getFirstName() + "\n";
			printOut += "Middle Initial: " + identityList.get(i).getMiddleInitial() + "\n";
			printOut += "Last Name: " + identityList.get(i).getLastName() + "\n";
			printOut += "Gender: " + identityList.get(i).getGender() + "\n";
			printOut += "Phone Number: " + identityList.get(i).getPhoneNumber() + "\n";
			printOut += "Email: " + identityList.get(i).getEmail() + "\n";
			printOut += "Address: " + identityList.get(i).getAddress() + "\n";
			printOut += "\n";
			
		}//end for
		
		// Set up the text area
		dialogBoxTextArea.setText(printOut); // add output string to text area
		dialogBoxTextArea.setBackground(Color.LIGHT_GRAY); // set the text area background color
		dialogBoxTextArea.setCaretPosition(0); // scrolls to the top of the text to start

		// Set up the GUI for the dialog box / pop up window
		formatDialogBox(dialogBoxGui);
		
		// Show the pop up window, add the text area, and display all the identities
		dialogBox.showMessageDialog(null, dialogBoxScrollPane, "All Identities", 
				JOptionPane.PLAIN_MESSAGE);
				
	}//end displayAll

	// Deletes currently displayed identity
	
	@SuppressWarnings("static-access") // suppress static warnings for UIManager dialogBoxGui
	private void deleteIdentity() {
		
		int userDecision = 1;
		String idNumber = "";
		JOptionPane dialogBox = new JOptionPane(); // create dialog box / pop up window
		UIManager dialogBoxGui = new UIManager(); // used to change the look and feel of dialog box
		
		// If the username is not blank
		if(!textFieldUsername.getText().equals("")) {
		
			// Traverse the list of identities
			for(int i=0; i<numberOfIdentities; i++) {
		
				// If the ID Number of the stored identity matches the number displayed in the field...
				if(identityList.get(i).getIdNumber().equals(textFieldIdNumber.getText())) {
				
					idNumber = textFieldIdNumber.getText();
				
				}//end if match
				
			}//end for
		
			// Set up the GUI for the dialog box / pop up window
			formatDialogBox(dialogBoxGui);
		
			// Create pop up asking if you are sure you want to delete
			userDecision = dialogBox.showConfirmDialog(null, 
					"Are you sure you want to delete this identity?", "Confirm Deletion", 
					JOptionPane.YES_NO_OPTION);

			// If YES (0)...
			if(userDecision == 0) {

				// Lower ID Number by -1
				idNumber = Integer.toString(Integer.valueOf(idNumber) - 1);

				identityList.remove(Integer.valueOf(idNumber)); // remove the identity from the list
			
				numberOfIdentities--; // reduce the total number of identities by -1
			
			}//end if YES
			else {
					
				// Do nothing for now
					
			}//end else NO (1)
			
			clearFields(); // clear all text fields
		
		}//end if
		
		// Disable Save, Delete, and Edit buttons
		buttonSave.setEnabled(false);
		buttonDelete.setEnabled(false);
		buttonEdit.setEnabled(false);
		
		// Re-enable New, Random, and DisplayAll buttons
		buttonNew.setEnabled(true);
		buttonRandom.setEnabled(true);
		buttonDisplayAll.setEnabled(true);
		
	}//end deleteIdentity
	
	// Display the identity passed in as an argument
	
	private void displayIdentity(Identity identity) {
		
		textFieldIdNumber.setText(identity.getIdNumber());
		textFieldTimesUsed.setText(identity.getTimesUsed());
		textFieldUsername.setText(identity.getUsername());
		textFieldPassword.setText(identity.getPassword());
		textFieldFirstName.setText(identity.getFirstName());
		textFieldMiddleInitial.setText(identity.getMiddleInitial());
		textFieldLastName.setText(identity.getLastName());
		textFieldGender.setText(identity.getGender());
		textFieldBirthdate.setText(identity.getBirthDate());
		textFieldPhoneNumber.setText(identity.getPhoneNumber());
		textFieldEmail.setText(identity.getEmail());
		textFieldAddress.setText(identity.getAddress());
		
	}//end displayIdentity

	// Set up the user interface
	
	private void setUpGui() {

		FlowLayout layout = new FlowLayout(); 
		
		layout.setVgap(0); // reduce the space in between panels
		setContentPane(new JLabel(new ImageIcon("background320x569wTitle.jpg"))); // set background image
		setLayout(layout); // arrange components left-to-right, top-to-bottom
		
		// Add panel for Title
		panelTitle = new JPanel(); // construct panel
	    panelTitle.setOpaque(false); // make transparent
	    panelTitle.setPreferredSize(new Dimension(250, 65)); // set size
	    add(panelTitle); // add panel to frame

		// Add panel (ID Number)
		panelOne = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
	    panelOne.setOpaque(false); // make transparent
	    panelOne.setPreferredSize(new Dimension(250, 30)); // set size
		add(panelOne); // add panel to frame
		// Add ID Number label and text field
		labelIdNumber = new JLabel(" ID Number "); // construct Label
		formatLabel(labelIdNumber); // format the label like all the rest
	    textFieldIdNumber = new JTextField("", 13); // construct TextField
	    textFieldIdNumber.setEditable(false); // set to not be able to edit text
	    panelOne.add(labelIdNumber); // add label to panel
	    panelOne.add(textFieldIdNumber); // add label to panel

		// Add panel (Times Used)
		panelTwo = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
	    panelTwo.setOpaque(false); // make transparent
	    panelTwo.setPreferredSize(new Dimension(250, 30)); // set size
	    add(panelTwo); // add panel to frame
	    // Add Times Used label and text field
 		labelTimesUsed = new JLabel(" Times Used "); // construct Label
 		formatLabel(labelTimesUsed); // format the label like all the rest
 	    textFieldTimesUsed = new JTextField("", 13); // construct TextField
 	    textFieldTimesUsed.setEditable(false); // set to not be able to edit text
 	    panelTwo.add(labelTimesUsed); // add label to panel
 	    panelTwo.add(textFieldTimesUsed); // add label to panel
	    
		// Add panel (Username)
		panelThree = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
	    panelThree.setOpaque(false); // make transparent
	    panelThree.setPreferredSize(new Dimension(250, 30)); // set size
	    add(panelThree); // add panel to frame
		// Add Username label and text field
		labelUsername = new JLabel(" Username "); // construct Label
		formatLabel(labelUsername); // format the label like all the rest
	    textFieldUsername = new JTextField("", 14); // construct TextField
	    textFieldUsername.setEditable(false); // set to not be able to edit text
	    panelThree.add(labelUsername); // add label to panel
	    panelThree.add(textFieldUsername); // add label to panel

	    // Add panel (Password)
 		panelFour = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelFour.setOpaque(false); // make transparent
 	    panelFour.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelFour); // add panel to frame
	    // Add Password label and text field
 		labelPassword = new JLabel(" Password "); // construct Label
 		formatLabel(labelPassword); // format the label like all the rest
 	    textFieldPassword = new JTextField("", 14); // construct TextField
 	    textFieldPassword.setEditable(false); // set to not be able to edit text
 	    panelFour.add(labelPassword); // add label to panel
 	    panelFour.add(textFieldPassword); // add label to panel
	    
	    // Add panel (First Name)
 		panelFive = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelFive.setOpaque(false); // make transparent
 	    panelFive.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelFive); // add panel to frame
 		// Add First Name label and text field
 		labelFirstName = new JLabel(" First Name "); // construct Label
 		formatLabel(labelFirstName); // format the label like all the rest
 	    textFieldFirstName = new JTextField("", 13); // construct TextField
 	    textFieldFirstName.setEditable(false); // set to not be able to edit text
 	    panelFive.add(labelFirstName); // add label to panel
 	    panelFive.add(textFieldFirstName); // add label to panel

 	    // Add panel (Middle Initial)
 		panelSix = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelSix.setOpaque(false); // make transparent
 	    panelSix.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelSix); // add panel to frame
 	    // Add Middle Initial label and text field
 		labelMiddleInitial = new JLabel(" Middle Initial "); // construct Label
 		formatLabel(labelMiddleInitial); // format the label like all the rest
 	    textFieldMiddleInitial = new JTextField("", 12); // construct TextField
 	    textFieldMiddleInitial.setEditable(false); // set to not be able to edit text
 	    panelSix.add(labelMiddleInitial); // add label to panel
 	    panelSix.add(textFieldMiddleInitial); // add label to panel
 	    
 	    // Add panel (Last Name)
 		panelSeven = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelSeven.setOpaque(false); // make transparent
 	    panelSeven.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelSeven); // add panel to frame
 	    // Add Last Name label and text field
 		labelLastName = new JLabel(" Last Name "); // construct Label
 		formatLabel(labelLastName); // format the label like all the rest
 	    textFieldLastName = new JTextField("", 14); // construct TextField
 	    textFieldLastName.setEditable(false); // set to be not able to edit text
 	    panelSeven.add(labelLastName); // add label to panel
 	    panelSeven.add(textFieldLastName); // add label to panel
 	    
	    // Add panel (Gender)
 		panelEight = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelEight.setOpaque(false); // make transparent
 	    panelEight.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelEight); // add panel to frame
 		// Add Gender label and text field
 		labelGender = new JLabel(" Gender "); // construct Label
 		formatLabel(labelGender); // format the label like all the rest
 	    textFieldGender = new JTextField("", 16); // construct TextField
 	    textFieldGender.setEditable(false); // set to not be able to edit text
 	    panelEight.add(labelGender); // add label to panel
 	    panelEight.add(textFieldGender); // add label to panel

	    // Add panel (Birthdate)
 		panelNine = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelNine.setOpaque(false); // make transparent
 	    panelNine.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelNine); // add panel to frame
 		// Add Birthdate label and text field
 		labelBirthdate = new JLabel(" Birthdate "); // construct Label
 		formatLabel(labelBirthdate); // format the label like all the rest
 	    textFieldBirthdate = new JTextField("", 14); // construct TextField
 	    textFieldBirthdate.setEditable(false); // set to not be able to edit text
 	    panelNine.add(labelBirthdate); // add label to panel
 	    panelNine.add(textFieldBirthdate); // add label to panel

	    // Add panel (Phone Number)
 		panelTen = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelTen.setOpaque(false); // make transparent
 	    panelTen.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelTen); // add panel to frame
 		// Add Phone Number label and text field
 		labelPhoneNumber = new JLabel(" Phone Number "); // construct Label
 		formatLabel(labelPhoneNumber); // format the label like all the rest
 	    textFieldPhoneNumber = new JTextField("", 11); // construct TextField
 	    textFieldPhoneNumber.setEditable(false); // set to not be able to edit text
 	    panelTen.add(labelPhoneNumber); // add label to panel
 	    panelTen.add(textFieldPhoneNumber); // add label to panel

	    // Add panel (Email)
 		panelEleven = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelEleven.setOpaque(false); // make transparent
 	    panelEleven.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelEleven); // add panel to frame
 		// Add Email label and text field
 		labelEmail = new JLabel(" Email "); // construct Label
 		formatLabel(labelEmail); // format the label like all the rest
 	    textFieldEmail = new JTextField("", 17); // construct TextField
 	    textFieldEmail.setEditable(false); // set to not be able to edit text
 	    panelEleven.add(labelEmail); // add label to panel
 	    panelEleven.add(textFieldEmail); // add label to panel

	    // Add panel (Address)
 		panelTwelve = new JPanel(new FlowLayout(FlowLayout.LEFT)); // construct panel, align to left
 	    panelTwelve.setOpaque(false); // make transparent
 	    panelTwelve.setPreferredSize(new Dimension(250, 30)); // set size
 	    add(panelTwelve); // add panel to frame
 		// Add Address label and text field
 		labelAddress = new JLabel(" Address "); // construct Label
 		formatLabel(labelAddress); // format the label like all the rest
 	    textFieldAddress = new JTextField("", 15); // construct TextField
 	    textFieldAddress.setEditable(false); // set to not be able to edit text
 	    panelTwelve.add(labelAddress); // add label to panel
 	    panelTwelve.add(textFieldAddress); // add label to panel
 	    
	    // Add first Button panel
 		panelButtons1 = new JPanel(); // construct panel, align to left
 	    panelButtons1.setOpaque(false); // make transparent
 		add(panelButtons1); // add to frame
 	    // Add button (New)
	    buttonNew = new JButton("NEW"); // construct Button
	    formatButton(buttonNew); // format the button like all the rest
	    panelButtons1.add(buttonNew); // add to panel
	    buttonNew.addActionListener(this); // when clicked send to ActionEvent
	    // Add button (Random)
	    buttonRandom = new JButton("RANDOM"); // construct Button
	    formatButton(buttonRandom); // format the button like all the rest
	    panelButtons1.add(buttonRandom); // add to panel
	    buttonRandom.addActionListener(this); // when clicked send to ActionEvent
 	    // Add button (Delete)
	    buttonDelete = new JButton("DELETE"); // construct Button
	    formatButton(buttonDelete); // format the button like all the rest
	    buttonDelete.setEnabled(false); // disable to start with
	    panelButtons1.add(buttonDelete); // add to panel
	    buttonDelete.addActionListener(this); // when clicked send to ActionEvent
	    
	    // Add second Button panel
	  	panelButtons2 = new JPanel(); // construct panel, align to left
	  	panelButtons2.setOpaque(false); // make transparent
	  	add(panelButtons2); // add to frame
	  	// Add button (Display All)
	    buttonDisplayAll = new JButton(" DISPLAY ALL "); // construct Button
	    formatButton(buttonDisplayAll); // format the button like all the rest
	    panelButtons2.add(buttonDisplayAll); // add to panel
	    buttonDisplayAll.addActionListener(this); // when clicked send to ActionEvent
	    // Add button (Edit)
	    buttonEdit = new JButton(" EDIT "); // construct Button
	    formatButton(buttonEdit); // format the button like all the rest
	    buttonEdit.setEnabled(false); // disable to start with
	    panelButtons2.add(buttonEdit); // add to panel
	    buttonEdit.addActionListener(this); // when clicked send to ActionEvent
	    // Add button (Save)
	    buttonSave = new JButton(" SAVE "); // construct Button
	    formatButton(buttonSave); // format the button like all the rest
	    buttonSave.setEnabled(false); // disable to start with
	    panelButtons2.add(buttonSave); // add to panel
	    buttonSave.addActionListener(this); // when clicked send to ActionEvent

	    // Add a panel for the text area
	  	panelTextArea = new JPanel(); // construct panel, align to left
	  	panelTextArea.setOpaque(false); // make transparent
	  	add(panelTextArea); // add to frame
	  	// Add text area
	    textArea = new JTextArea("",3,26); // construct text area
	    textArea.setBorder(BorderFactory.createLineBorder(Color.black)); // give it a border
	    textArea.setBackground(Color.LIGHT_GRAY); // set the background color
	    textArea.setEditable(false); // set to not be able to edit text
	    textArea.setLineWrap(true); // make sure the lines wrap
	    textAreaScrollPane = new JScrollPane(textArea); // give the text area a scrollbar
	    panelTextArea.add(textAreaScrollPane); // add to panel
	    
	    // Set up frame
	    setTitle("Identeco"); // "super" Frame sets title
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the "X" button work
	    pack(); // causes this window to be sized to fit preferred size/layouts of subcomponents
	    setLocationRelativeTo(null); // center Frame on screen
	    setVisible(true); // "super" Frame shows
	
	}//end setUpGui

	// Format the button passed in as an argument
	
	private void formatButton(JButton button) {
		
	    button.setForeground(Color.white); // set font color
	    button.setBackground(Color.black); // set background color
	    button.setFont(new Font("Calibri", Font.BOLD, 16)); // set font
		
	}//end formatButton
	
	// Format the dialog box user interface passed in as an argument
	
	@SuppressWarnings("static-access") // suppress static warnings for UIManager dialogBoxGui
	private void formatDialogBox(UIManager dialogBoxGui) {
		
		// Set up the GUI for the dialog box / pop up window
		dialogBoxGui.put("OptionPane.background", Color.DARK_GRAY); // set background color
		dialogBoxGui.put("OptionPane.messageForeground", Color.WHITE); // set font color
		dialogBoxGui.put("Panel.background", Color.DARK_GRAY); // set background color
		dialogBoxGui.put("Button.background", Color.BLACK); // set button background color
		dialogBoxGui.put("Button.buttonFont", new Font("Calibri", Font.BOLD, 16)); // button font
		dialogBoxGui.put("Button.foreground", Color.WHITE); // set button font color
		
	}//end formatDialogueBox
	
	// Format the label passed in as an argument
	
	private void formatLabel(JLabel label) {
		
		label.setForeground(Color.WHITE); // set text color
		label.setFont(new Font("Calibri", Font.BOLD, 16)); // set font
		label.setBackground(Color.gray); // set background color
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setOpaque(true); // make opaque
		
	}//end formatLabel
	
	// Clear all text fields
	
	private void clearFields() {
		
		textFieldIdNumber.setText("");
		textFieldTimesUsed.setText("");
		textFieldUsername.setText("");
		textFieldPassword.setText("");
		textFieldFirstName.setText("");
		textFieldMiddleInitial.setText("");
		textFieldLastName.setText("");
		textFieldGender.setText("");
		textFieldBirthdate.setText("");		
		textFieldPhoneNumber.setText("");
		textFieldEmail.setText("");
		textFieldAddress.setText("");
		
	}//end clearFields

	// Enable editing in all text fields
	
	@SuppressWarnings("unused") // This method is currently unused, but that's OK
	private void enableFields() {
		
		//textFieldIdNumber.setEditable(true);
		//textFieldTimesUsed.setEditable(true);
		textFieldUsername.setEditable(true);
		textFieldPassword.setEditable(true);
		textFieldFirstName.setEditable(true);
		textFieldMiddleInitial.setEditable(true);
		textFieldLastName.setEditable(true);
		textFieldGender.setEditable(true);
		textFieldBirthdate.setEditable(true);
		textFieldPhoneNumber.setEditable(true);
		textFieldEmail.setEditable(true);
		textFieldAddress.setEditable(true);
		
	}//end enableFields
	
	// Disable editing in all text fields
	
	private void disableFields() {
		
		textFieldIdNumber.setEditable(false);
		textFieldTimesUsed.setEditable(false);
		textFieldUsername.setEditable(false);
		textFieldPassword.setEditable(false);
		textFieldFirstName.setEditable(false);
		textFieldMiddleInitial.setEditable(false);
		textFieldLastName.setEditable(false);
		textFieldGender.setEditable(false);
		textFieldBirthdate.setEditable(false);
		textFieldPhoneNumber.setEditable(false);
		textFieldEmail.setEditable(false);
		textFieldAddress.setEditable(false);
		
	}//end disableFields
	

}//end class Identeco