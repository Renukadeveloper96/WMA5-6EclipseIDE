package collection;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Vector;

public class ArrayListDem3 {

	public static void main(String[] args) {
		Vector<Integer> al=new Vector<Integer>();
		al.add(10);
		al.add(25);
		al.add(50);

		System.out.println(al);
		
		Enumeration e=al.elements();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement()+" ");
		}


	}

}
