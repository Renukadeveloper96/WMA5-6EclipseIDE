package thread;

public class ThreadBasicRunner {

	public static void main(String[] args) {
		Task1 task1=new Task1();
		task1.start();
		
		//task2
		for(int i=201;i<=299;i++) {
			System.out.println(i+ " ");
		}
		System.out.println("Task2 done");
		
		for(int i=301;i<=399;i++) {
			System.out.println(i+ " ");
		}
		System.out.println("Task3 done");
	}

}
