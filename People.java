import java.util.Date;

public class People implements Person {
	
	public String name;
	public String address;
	public Date birthday;
	
	public People(String name, String address, Date birthday) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}

	public String getName() {
		return this.name;		
	}
	public String getAddress() {
		return this.address;		
	}
	public Date getBirthday() {
		return this.birthday;		
	}
}
