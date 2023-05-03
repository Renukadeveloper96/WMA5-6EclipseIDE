package wma3;

public class nestedIfStatments {

	public static void main(String[] args) {
		String name="Kajol,jyothi,rashmi";
		if(name.endsWith("rashmi")) {
			if(name.contains("Ashwini")) {
				System.out.println("Ashwini");
			}
			else if(name.contains("jyothi")){
				System.out.println("jyothi");
			}
		}
		else {
			System.out.println("your name is not available");
		}

	}

}
