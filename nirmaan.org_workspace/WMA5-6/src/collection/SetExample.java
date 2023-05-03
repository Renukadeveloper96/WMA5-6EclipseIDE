package collection;

import java.util.HashSet;

public class SetExample {

	public static void main(String[] args) {
	HashSet<Integer>hs=new HashSet<Integer>();
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
