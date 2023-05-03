package exception;

public class JavaException extends Throwable{

	public static void main(String[] args)  {
		String s= null;
			try 
			{
				System.out.println("string values");
			}catch(NullPointerException e) {
				System.out.println(e);
			}
	}

}
