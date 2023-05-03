package oops;

public class EncapsulationStudentExample {

	private String name;
	private int rollNumber;
	private String batch;
	
	public EncapsulationStudentExample() {}
	public EncapsulationStudentExample(String name, int rollNumber, String batch) {
		super();
		this.name = name;
		this.rollNumber = rollNumber;
		this.batch = batch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "EncapsulationStudentExample [name=" + name + ", rollNumber=" + rollNumber + ", batch=" + batch + "]";
	}
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	
	
	

}
