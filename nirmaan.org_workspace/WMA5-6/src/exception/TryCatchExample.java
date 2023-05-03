package exception;

import java.util.Arrays;
import java.util.Scanner;

public class TryCatchExample {

	public static void main(String[] args) {
//		int[] myArray = {10,20,30};// 7 element
//	      System.out.println("Elements in the array are:: ");
//	      System.out.println(Arrays.toString(myArray));
//	      Scanner sc = new Scanner(System.in);
//	      System.out.println("Enter the index of the required element ::");
//	      try {
//	         int element = sc.nextInt();
//	         System.out.println("Element in the given index is :: "+myArray[element]);
//	      } catch(ArrayIndexOutOfBoundsException e) {
//	         System.out.println("The index you have entered is invalid");
//	         System.out.println("Please enter an index number between 0 and 6");
//	      }
			
		
		String ptr = null;
		 
        try
        {
            // This line of code throws NullPointerException
            // because ptr is null
            if (ptr.equals("gfg"))
                System.out.print("Same");
            else
                System.out.print("Not Same");
            
          
        }
        catch(NullPointerException e)
        {
            System.out.print("NullPointerException Caught");
        }
        
        
	}
	}


