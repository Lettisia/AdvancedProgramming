package Assign2.Algorithm;

/**
 * Contains data common to all participants in the Ozlympics game, namely
 * userID, name, age and state.
 * 
 * @author Lettisia George
 *
 */
public abstract class Participant {
	private String userID;
	private String name;
	private int age;
	private String state;

	public Participant(String userID, String name, int age, String state) {
		this.userID = userID;
		this.name = name;
		this.age = age;
		this.state = state;
	}

	@Override
	public String toString() {
		return userID + ", " + name + ", " + age + ", " + state;
	}

	public abstract String getType();

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
