package oops;

public class Child extends Parent{  
	
	void eat(){System.out.println("child...");}  
	
	public static void main(String[] args) {
		Parent parent=new Parent();
		parent.eat();//parents...

		
		Parent parent1=new Child();
		parent1.eat();//child...
		
		Child child=new Child();
		child.eat();
		
		
	}
	}
