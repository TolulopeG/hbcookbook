package org.com.hbcookbook;

public class Recipe {

	private String recipeName;
	private String ingredients;
	private String instructions;
	private String appliances;

	public Recipe(String recipeName, String ingredients, String instructions, String appliances) {
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.appliances = appliances;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public String getIngredients() {
		return ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public String getAppliances() {
		return appliances;
	}

}