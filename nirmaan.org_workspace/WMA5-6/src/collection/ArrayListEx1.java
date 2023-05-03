package collection;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx1 {

	public static void main(String[] args) {
		List l=new ArrayList();
		l.add(100);//index of 0  
		l.add(100);//index of 1
		l.add(200);//index of 2
		l.add(300);//index of 3
		l.add("raju");//index of 4
		l.add("rani");//index of 5
		l.add(100.60);//index of 6
//		l.set(1, 600);
		l.add(1,700);
//		l.add(1, 23);
		System.out.println(l);

	}

}
