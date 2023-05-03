package exception;

public class arrayIndexOutofBoundException {

	public static void main(String[] args) {
		
		int a[]= new int[5];
		
		a[0]=10;
		a[1]=20;
		a[2]=30;
		a[3]=40;
		a[4]=50;
		a[5]=60;
		System.out.println(a[2]);

	}

}
