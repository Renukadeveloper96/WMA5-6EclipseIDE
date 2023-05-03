package collection;

import java.util.LinkedHashSet;

public class LinkedHashSetExample {

	public static void main(String[] args) {
		LinkedHashSet<Integer>hs=new LinkedHashSet<Integer>();
		
		hs.add(27);
		hs.add(12);
		hs.add(null);
		hs.add(9);
		hs.add(21);
		hs.add(21);
		System.out.println(hs.size());
		for(Integer e:hs) {
			System.out.println(e);
		}
	}

}
