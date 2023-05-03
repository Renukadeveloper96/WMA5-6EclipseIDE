package oops;

public abstract class AbstractRecipe {

	public void excecute() {
		prepareIngredient();
		cookeRecipe();
		cleaUp();
	}
	abstract void prepareIngredient();
	abstract void cookeRecipe();
	abstract void cleaUp();
}
