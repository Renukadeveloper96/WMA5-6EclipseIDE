package comparator;

import java.util.Comparator;

public class FeesComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		
		
		Student s1=(Student)o1;
		Student s2=(Student)o2;  
		
		
		if(s1.fees==s2.fees)  
			return 0;  
			else if(s1.fees>s2.fees)  
			return 1;  
			else  
			return -1;  
	}

}
