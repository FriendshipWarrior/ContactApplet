package validation;

public class Validation 
{
	public static boolean validateFirstName(String firstName)
	{
		return firstName.matches("[A-Z][a-zA-Z]*");
	}
	
	public static boolean validateLastName(String lastName)
	{
		return lastName.matches("[a-zA-Z]+(['-][a-zA-Z]+)*");
	}
	
	public static boolean validateAddress(String address)
	{
		return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	}
	
	public static boolean validateCity(String city)
	{
		return city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
	}
	
	public static boolean validateState(String state)
	{
		return state.matches("([a-zA-Z]+|[a_zA-Z]+\\s[a-zA-Z]+)");
	}
	
	public static boolean validateZip(String zip)
	{
		return zip.matches("\\d{5}");
	}
	
	public static boolean validatePhoneNumber(String phoneNumber)
	{
		return phoneNumber.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
	}
	
	public static boolean validateEmail(String email)
	{
		return email.matches("\\s?<?[a-z\\._0-9]+[^\\._]@([a-z0-9]+\\.)+[a-z0-9]{2,6}>?;?");
	}
	
	public static boolean validateWorkNumber(String workNumber)
	{
		return workNumber.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
	}
}
