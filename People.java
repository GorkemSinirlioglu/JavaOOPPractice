import java.util.Date;

/**
 * Die People Klasse, hat grundlegende Eigenschaften einer normalen Person
 * @author Görkem Sinirlioglu
 * @version 2.3.5
 */
public class People implements Person {
	
	protected String name;
	protected String address;
	protected Date birthday;
	
	/**
	 * Konstruktor zum Erzeugen eines People
	 * @param name Voller Name in String
	 * @param address Addresse als String
	 * @param birthday Geburtsdatum als Date
	 */
	public People(String name, String address, Date birthday) {
		this.name = name;
		this.address = address;
		this.birthday = birthday;
	}
	
	/**
	 * Getter Methode für den Namen
	 * @return Voller Name des People
	 */
	public String getName() {
		return this.name;		
	}
	
	/**
	 * Getter Methode für die Addresse
	 * @return Addresse des People
	 */
	public String getAddress() {
		return this.address;		
	}
	
	/**
	 * Getter Methode für das Geburtsdatum
	 * @return Geburtsdatum des People
	 */
	public Date getBirthday() {
		return this.birthday;		
	}
}
