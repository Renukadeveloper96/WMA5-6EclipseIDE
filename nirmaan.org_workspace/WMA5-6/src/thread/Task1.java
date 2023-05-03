package thread;

public class Task1 extends Thread{

	
	public void run() {
		for(int i=0;i<=199;i++) {
			System.out.println(i+ " ");
		}
		System.out.println("Task1 done");
	}
}
