package collection;

import java.util.Vector;

public class VectorExample {

	public static void main(String[] args) {
		Vector <String>v=new Vector<String>();
		v.add("sachin");
		v.add(new String("sehwagh"));
		v.add("dhoni");
		v.add("suresh");
		System.out.println(v);
		System.out.println(v.size());
	}

}
