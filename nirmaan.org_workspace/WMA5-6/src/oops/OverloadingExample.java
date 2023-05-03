package oops;

public class OverloadingExample {

	static int add(int a,int b){
		return a+b;
		}  
	static int add(int a,int b,int c){
		return a*b*c;
		}  
	
public static void main(String[] args) {
	
	OverloadingExample overloadingExample=new OverloadingExample();
	System.out.println(overloadingExample.add(10,20));
	System.out.println(overloadingExample.add(1,2,3));
}
}

