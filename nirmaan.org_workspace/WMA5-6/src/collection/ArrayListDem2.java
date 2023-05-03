package collection;

import java.util.ArrayList;
import java.util.ListIterator;

public class ArrayListDem2 {

	public static void main(String[] args) {
		ArrayList<String> al=new ArrayList<>();
		al.add("nirmaan");
		al.add("it");
		al.add("java");
		al.add("training");

		System.out.println(al);
		ListIterator<String>listiterator=al.listIterator();
		
		while(listiterator.hasNext()) {
			String next=listiterator.next();
			System.out.println(next);
		}

	}

}
