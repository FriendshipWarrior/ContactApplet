package models;

import java.io.Serializable;

/**
 * @author the_friendship_warrior
 *
 */
public class Person implements Serializable
{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -1604475661453004150L;
	
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;
	private String birthday;
	
	// work attributes
	private String company;
	private String workPhone;
	
	// person attributes
	private String hobby;
	private String nickName;
	
	// users relationship with this person
	private String relationship;

	public Person()
	{
		super();
	}

	public Person(String fn, String ln, String a, String c, String s,
			String z, String pn, String e)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.address = a;
		this.city = c;
		this.zip = z;
		this.phoneNumber = pn;
		this.email = e;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setFirstName(String fn)
	{
		this.firstName = fn;
	}
	
	public void setLastName(String ln)
	{
		this.lastName = ln;
	}
	
	public void setAddress(String a)
	{
		this.address = a;
	}
	
	public void setCity(String c)
	{
		this.city = c;
	}
	
	public void setState(String s)
	{
		this.state = s;
	}
	
	public void setZip(String z)
	{
		this.zip = z;
	}
	
	public void setPhoneNumber(String pn)
	{
		this.phoneNumber = pn;
	}
	
	public void setEmail(String z)
	{
		this.zip = z;
	}
	
}
