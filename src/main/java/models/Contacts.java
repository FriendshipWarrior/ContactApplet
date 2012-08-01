package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contacts implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5774052524831953967L;
	
	private List<Person> friends;
	private List<Person> family;
	private List<Person> work;
	
	public Contacts() {
		friends = new ArrayList<Person>(5);
		family = new ArrayList<Person>(5);
		work = new ArrayList<Person>(5);
	}
	
	public void addPerson(Person p, Relationship r) {
		if (r instanceof BusinessRelationship) {
			work.add(p);
		}
		else if (r instanceof FriendRelationship) {
			friends.add(p);
		}
		else {
			family.add(p);
		}
	}
	
	public void removePerson(Person p) {
		// search all the lists; kill person
	}
	
	public int length() {
		return friends.size() + family.size() + work.size();
	}
}
