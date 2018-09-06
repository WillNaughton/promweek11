package entity;

import java.util.List;

public class Family {

	private int familyId;	
	private String lastName;
	private String firstName;
	private int age;
	private String relation;
	private List<Family> members;
	
	public Family(int familyId, String lastName, String firstName,int age, String relation, List<Family> members) {
		this.setFamilyId(familyId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setRelation(relation);
	}

	public List<Family> getMembers() {
		return members;
	}

	public void setMembers(List<Family> members) {
		this.members = members;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
}
