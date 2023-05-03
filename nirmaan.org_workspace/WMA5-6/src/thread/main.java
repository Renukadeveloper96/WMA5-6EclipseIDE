package thread;

public class main implements Runnable{

	public void run() {
		System.out.println("This code is running in a thread");
	}
	public static void main(String[] args) {
		main m=new main();
		m.run();
	}

}
