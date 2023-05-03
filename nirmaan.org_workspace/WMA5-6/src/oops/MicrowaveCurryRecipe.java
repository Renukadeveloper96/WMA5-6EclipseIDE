package oops;

public class MicrowaveCurryRecipe extends  AbstractRecipe{

	
	public MicrowaveCurryRecipe() {
		System.out.println("Microwave Curry Recipe ");
	}
	@Override
	void prepareIngredient() {
		
		System.out.println("Microwave Get vegtables");		
	}

	@Override
	void cookeRecipe() {
		System.out.println("Microwave stream and dry foods");
	}

	@Override
	void cleaUp() {
		System.out.println("Microwave Discard unused vegetable");
	}
	
	
	public static void main(String[] args) {
		
		CurryRecipe curryRecipe=new CurryRecipe();
		curryRecipe.excecute();
		MicrowaveCurryRecipe microwaveCurryRecipe=new MicrowaveCurryRecipe();
		microwaveCurryRecipe.excecute();
	}

}
