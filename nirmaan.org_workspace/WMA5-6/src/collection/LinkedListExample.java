package collection;

import java.util.LinkedList;

public class LinkedListExample {

	public static void main(String[] args) {
		LinkedList al=new LinkedList();
		al.add("brudha");
		al.add(101);
		al.add(202.05);
		System.out.println(al);
		al.remove(1);
		System.out.println(al);
		al.add("hubli");
		al.add("java");
		al.add("java");
		al.add(null);
		System.out.println( "last index of array "+al.lastIndexOf("hubli"));
		System.out.println(al);
		al.removeLast();
		al.addFirst("jyothi");
		System.out.println("removed last element" +al);
	
	}

}
