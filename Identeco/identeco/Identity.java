package identeco;

public class Identity {

	// Create all the variables
    private String idNumber;
    private String timesUsed;
    private String username;
    private String password;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String address;
    	
    // Constructor
    public Identity() 
    {
    	// Initialize all the variables to "0" or "null"
        idNumber = "0";
        timesUsed = "0";
        username = null;
        password = null;
        firstName = null;
        middleInitial = null;
        lastName = null;
        gender = null;
        birthDate = null;
        phoneNumber = null;
        email = null;
        address = null;
    	
    };//end constructor
	
    // Get ID Number
    public String getIdNumber() {
    	return idNumber;
    }//end getIdNumber

    // Get Times Used
    public String getTimesUsed() {
    	return timesUsed;
    }//end getTimesUsed
    
    // Get Username
    public String getUsername() {
    	return username;
    }//end getUsername
    
    // Get Password
    public String getPassword() {
    	return password;
    }//end getPassword

    // Get First Name
    public String getFirstName() {
    	return firstName;
    }//end getFirstName

    // Get Middle Initial
    public String getMiddleInitial() {
    	return middleInitial;
    }//end getMiddleInitial
    
    // Get Last Name
    public String getLastName() {
    	return lastName;
    }//end getLastName
    
    // Get Gender
    public String getGender() {
    	return gender;
    }//end getGender
    
    // Get Birth Date
    public String getBirthDate() {
    	return birthDate;
    }//end getBirthDate
    
    // Get Phone Number
    public String getPhoneNumber() {
    	return phoneNumber;
    }//end getPhoneNumber
    
    // Get Email
    public String getEmail() {
    	return email;
    }//end getEmail
    
    // Get Address
    public String getAddress() {
    	return address;
    }//end getAddress
    
    // Set ID Number
    public void setIdNumber(String id) {
    	idNumber = id;
    }//end setIdNumber

    // Set Times Used
    public void setTimesUsed(String times) {
    	timesUsed = times;
    }//end setTimesUsed
    
    // Set Username
    public void setUsername(String user) {
    	username = user;
    }//end setUsername
    
    // Set Password
    public void setPassword(String pword) {
    	password = pword;
    }//end setPassword
    
    // Set First Name
    public void setFirstName(String first) {
    	firstName = first;
    }//end setFirstName

    // Set Middle Initial
    public void setMiddleInitial(String middle) {
    	middleInitial = middle;
    }//end setMiddleInitial
    
    // Set Last Name
    public void setLastName(String last) {
    	lastName = last;
    }//end setLastName
    
    // Set Gender
    public void setGender(String maleOrFemale) {
    	gender = maleOrFemale;
    }//end setGender
    
    // Set Birth Date
    public void setBirthDate(String date) {
    	birthDate = date;
    }//end setBirthDate
    
    // Set Phone Number
    public void setPhoneNumber(String phone) {
    	phoneNumber = phone;
    }//end setPhoneNumber
    
    // Set Email
    public void setEmail(String emailAddress) {
    	email = emailAddress;
    }//end setEmail
    
    // Set Address
    public void setAddress(String physicalAddress) {
    	address = physicalAddress;
    }//end setAddress
    
    public void updateTimesUsed() {

    	try {
    		timesUsed = Integer.toString(Integer.valueOf(timesUsed) + 1);
    	} catch (NumberFormatException e) {
    		System.err.println("Check that there are no doubled-up lines in source file.");
    	}//end try/catch
    	
    
    }//end updateTimesUsed
    
}//end class Identity