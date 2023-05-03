package constructor;

public class Employee {

	
	int empid;
	double empSalary;
	String empName;
	String empMobileNo;
	String empAddress;
	
	public Employee(int empid, double empSalary, String empName, String empMobileNo, String empAddress) {
		super();
		this.empid = empid;
		this.empSalary = empSalary;
		this.empName = empName;
		this.empMobileNo = empMobileNo;
		this.empAddress = empAddress;
	}
 void empDisplay() {
	 System.out.println("Employee Details \n Employee Id:" +empid+ " \nEmployee Name:"+empName);
 }
public static void main(String[] args) {
	Employee employee=new Employee(101,18000,"jyothi","1334556677","DHARWAD");
	employee.empDisplay();
}

	
}
