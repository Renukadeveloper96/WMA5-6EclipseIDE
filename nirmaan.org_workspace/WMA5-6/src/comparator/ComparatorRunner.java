package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ComparatorRunner {

	public static void main(String[] args) {
		
		ArrayList al=new ArrayList();  
		al.add(new Student(101,"Vijay",23,20000));  
		al.add(new Student(106,"Ajay",27,40000));  
		al.add(new Student(105,"Jai",21,50000));  
		  
		System.out.println("Sorting by Name");  
		  
		Collections.sort(al,new NameComparator());  
		Iterator itr=al.iterator();  
		while(itr.hasNext()){  
		Student st=(Student)itr.next();  
		System.out.println(st.rollno+" "+st.name+" "+st.age+" "+st.fees);  
		}  
//		  
		System.out.println("Sorting by age");  
//		  
		Collections.sort(al,new AgeComparator());  
		Iterator itr2=al.iterator();  
		while(itr2.hasNext()){  
		Student st=(Student)itr2.next();  
		System.out.println(st.rollno+" "+st.name+" "+st.age+" "+st.fees);  
		}  
		
		  
		System.out.println("Sorting by fees");  
//				  
		Collections.sort(al,new FeesComparator());  
		Iterator itr3=al.iterator();  
		while(itr3.hasNext()){  
		Student st=(Student)itr3.next();  
		System.out.println(st.rollno+" "+st.name+" "+st.age+" "+st.fees);  
		 
	}
		}
}

