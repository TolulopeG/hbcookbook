package org.com.hbcookbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeManager {

	private static final String RECIPES_DIRECTORY = "data/recipes/";

	public RecipeManager() {
		File directory = new File(RECIPES_DIRECTORY);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	public void addRecipe(String username, Recipe recipe) {
		String userDir = RECIPES_DIRECTORY + username + "/";
		File directory = new File(userDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		try (PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(userDir + recipe.getRecipeName() + ".txt")))) {
			out.println(recipe.getIngredients());
			out.println(recipe.getInstructions());
			out.println(recipe.getAppliances());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Recipe> getRecipes(String username) {
		List<Recipe> recipes = new ArrayList<>();
		String userDir = RECIPES_DIRECTORY + username + "/";
		File directory = new File(userDir);
		if (directory.exists()) {
			for (File file : Objects.requireNonNull(directory.listFiles())) {
				try (BufferedReader br = new BufferedReader(new FileReader(file))) {
					String title = file.getName().replace(".txt", "");
					String ingredients = br.readLine();
					String instructions = br.readLine();
					String appliances = br.readLine();
					recipes.add(new Recipe(title, ingredients, instructions, appliances));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return recipes;
	}

	public void deleteRecipe(String username, String title) {
		String userDir = RECIPES_DIRECTORY + username + "/";
		File file = new File(userDir + title + ".txt");
		if (file.exists()) {
			file.delete();
		}
	}

	public void editRecipe(String username, Recipe recipe) {
		deleteRecipe(username, recipe.getRecipeName());
		addRecipe(username, recipe);
	}

}
