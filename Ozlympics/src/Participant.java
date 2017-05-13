/**
 * Contains data common to all participants in the Ozlympics game, namely userID, name, age and state.
 * @author Lettisia George
 *
 */
public abstract class Participant implements Comparable<Participant> {
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
	
	@Override
	public int compareTo(Participant arg0) {
		return this.getName().compareTo(arg0.getName());
	}
	
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}
