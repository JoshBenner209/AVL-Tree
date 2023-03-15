/*
  Josh Benner
  Cs 145
  Lab 6
  March 6
  PersonInfo stores all personal info about
  employee. uses getters and setters to maipulate
  the fields.
 */
public class PersonInfo {
    private String firstName;
    private String lastName;
    private String address;
    private String  phoneNumb;
    private String city;
    private String  email;
    private String zip;
    private String state;
	private Integer id;
    public PersonInfo (String firstName,String lastName,String state,
    		String address, String phoneNumb,String city,String zip, String email, Integer id){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setPhoneNumb(phoneNumb);
        this.setCity(city);
        this.setZip(zip);
        this.setState(state);
        this.setEmail(email);
		this.setId(id);
    }
    
    private void setId(Integer id) {
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public PersonInfo (){
        this(null,null,null,null,null,null,null,null,0);
    }
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String  lastName) {
		this.lastName = lastName;
	}
	public String  getFirstName() {
		return firstName;
	}
	public void setFirstName(String  firstName) {
		this.firstName = firstName;
	}
	public String getPhoneNumb() {
		return phoneNumb;
	}
	public void setPhoneNumb(String  phoneNumb) {
		this.phoneNumb = phoneNumb;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String  city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip=zip;
	}
	public String toString() {
		return firstName+" "+lastName+"\n"+email+"\n"+phoneNumb+"\n"
				+address+" "+city+", "+state+" "+zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
