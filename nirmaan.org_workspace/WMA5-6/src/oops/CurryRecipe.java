package oops;

public class CurryRecipe extends AbstractRecipe{
	
	public CurryRecipe() {
		
		System.out.println("Curry Recipe ");
	}

	@Override
	void prepareIngredient() {
		System.out.println("Get vegtables");
		
	}

	@Override
	void cookeRecipe() {
		System.out.println("stream and dry foods");
	}

	@Override
	void cleaUp() {
		System.out.println("Discard unused vegetable");
	}

}
