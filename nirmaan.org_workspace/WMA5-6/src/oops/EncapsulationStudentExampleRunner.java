package oops;

public class EncapsulationStudentExampleRunner {

	public static void main(String[] args) {
		EncapsulationStudentExample encapsulationStudentExample=new EncapsulationStudentExample("pooja",121,"WMA3");
//		encapsulationStudentExample.setName("pooja");
//		System.out.println(encapsulationStudentExample.getName());
		System.out.println("Student details :" +encapsulationStudentExample.getName()+" "+encapsulationStudentExample.getRollNumber()+" "+encapsulationStudentExample.getBatch());

	}

}
