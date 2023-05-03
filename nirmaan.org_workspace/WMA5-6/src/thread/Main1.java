package thread;

public class Main1 extends Thread{
	
	public static int amount=5;

	
	public static void main(String[] args) {
		Main1 main1=new Main1();
		main1.start();
		System.out.println(amount+1);//2
//		amount++;
//		System.out.println(amount);//2+1=3


	}
	public void run() {
		amount++;//3+1=4
	}

}
