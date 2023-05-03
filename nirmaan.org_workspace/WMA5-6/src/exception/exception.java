package exception;
//Java program to demonstrate ArithmeticException

class exception
{
	public static void main(String args[])
	{
////		int a = 20, b = 0;
//		int a[]=new int[5];//5-1 means 0th index to n-1
//		try {
//        	a[5]=20;
////			int c = a/b; // cannot divide by zero
////			System.out.println ("Result = " + c);
//			System.out.println(a[1]);
//		}
//		catch(ArithmeticException e) {
////			System.out.println ("Can't divide a number by 0");
////			System.out.println(e);
//			System.out.println("Index 10 out of bounds for length 5:fail");
//		}
		
		 int ar[] = { 1, 2, 3, 4, 5 };
	        try {
	            for (int i = 0; i <= ar.length; i++)
	                System.out.print(ar[i]+" ");
	        }
	        catch (Exception e) {
	            System.out.println("\nException caught");
	        }
	}
}
