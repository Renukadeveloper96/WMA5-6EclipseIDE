package oops;



public class KurthaRunner implements kurthayellow, kurthablue{

	

	@Override
	public void print() {
		System.out.println("Women Printed Viscose Rayon Ethnic Dress");
		System.out.println("PRICE:436");
		
	}
	
	@Override
	public void kurthasize() {
		System.out.println("kurthayellow:xxl");
		System.out.println("kurthablue:xl");
	}
	
	public static void main(String[] args) {
		KurthaRunner kurthaRunner=new KurthaRunner();
		kurthaRunner.print();
		kurthaRunner.kurthasize();
	}

	
	
		

}
