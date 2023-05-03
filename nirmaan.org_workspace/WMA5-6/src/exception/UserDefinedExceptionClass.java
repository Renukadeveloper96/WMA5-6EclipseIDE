package exception;

public class UserDefinedExceptionClass {

	public static void main(String[] args) {
		try {
			
			throw new AnException(22);
		}
		catch(AnException e){
			System.out.println("catch block");
//			System.out.println(e);
		}
	}

}
