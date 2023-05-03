package collection;

import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		ArrayList al=new ArrayList();
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
		System.out.println(al);
	}

}
