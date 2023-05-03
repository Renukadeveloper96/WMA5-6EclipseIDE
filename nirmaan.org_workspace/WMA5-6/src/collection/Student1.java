package collection;

public class Student1 {

	private String name;
	private int sId;

    public Student1() {};
    
    
    public Student1(String name, int sId) {
		super();
		this.name = name;
		this.sId = sId;
	}
	public static void main(String[] args) {
        Student1 s[]=new Student1[5];
        s[0]=new Student1("ravi",101);
        s[1]=new Student1("raju",102);  
        s[4]=new Student1("Nagaveni",104);
//        s[5]=new Student1("Rashmi");
        s[2]=new Student1("rani",105);
//        s[3]=new Employee(101,"Ramesh");
        System.out.println(s[2]);
    
    }
	@Override
	public String toString() {
		return "Student1 [name=" + name + ", sId=" + sId + "]";
	}
}
