package org.com.hbcookbook;

import java.util.Scanner;

public class App {
	private static UserManager userManager = new UserManager();
	private static RecipeManager recipeManager = new RecipeManager();
	private static User currentUser;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome to the HB Cookbook App");

		// User Authentication
		System.out.print("Enter username: ");
		String username = scan.nextLine();
		System.out.print("Enter password: ");
		String password = scan.nextLine();

		if (userManager.authenticate(username, password)) {
			currentUser = userManager.getUser(username);
			System.out.println("Login successful!");
			showMenu(scan);
		} else {
			System.out.println("Invalid username or password");
		}

		scan.close();
	}

	private static void showMenu(Scanner scan) {

		while (true) {
			System.out.println("1. Add Recipe");
			System.out.println("2. List Recipes");
			System.out.println("3. Delete Recipe");
			System.out.println("4. Edit Recipe");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");
			int option = Integer.parseInt(scan.nextLine());

			switch (option) {
			case 1:
				addRecipe(scan);
				break;
			case 2:
				listRecipes();
				break;
			case 3:
				deleteRecipe(scan);
				break;
			case 4:
				editRecipe(scan);
				break;
			case 5:
				System.out.println("Thank you!");
				return;
			default:
				System.out.println("Invalid option. Try again.");
			}
		}
	}

	private static void addRecipe(Scanner scan) {
		System.out.print("Enter recipe Name: ");
		String recipeName = scan.nextLine();
		System.out.print("Enter ingredients: ");
		String ingredients = scan.nextLine();
		System.out.print("Enter instructions: ");
		String instructions = scan.nextLine();
		System.out.print("Enter appliances to use: ");
		String appliances = scan.nextLine();
		Recipe recipe = new Recipe(recipeName, ingredients, instructions, appliances);
		recipeManager.addRecipe(currentUser.getUsername(), recipe);
		System.out.println("Recipe added successfully!");

	}

	private static void deleteRecipe(Scanner scan) {
		System.out.print("Enter recipe name to delete: ");
		String recipeName = scan.nextLine();
		recipeManager.deleteRecipe(currentUser.getUsername(), recipeName);
		System.out.println("Recipe deleted successfully!");

	}

	private static void listRecipes() {
		System.out.println("Your Recipes:");
		for (Recipe recipe : recipeManager.getRecipes(currentUser.getUsername())) {
			System.out.println("Recipe Name: " + recipe.getRecipeName());
			System.out.println("Ingredients: " + recipe.getIngredients());
			System.out.println("Instructions: " + recipe.getInstructions());
			System.out.println("Appliances: " + recipe.getAppliances());
			System.out.println("-----");
		}
	}

	private static void editRecipe(Scanner scan) {
		System.out.print("Enter recipe name to edit: ");
		String recipeName = scan.nextLine();
		System.out.print("Enter new ingredients: ");
		String ingredients = scan.nextLine();
		System.out.print("Enter new instructions: ");
		String instructions = scan.nextLine();
		System.out.print("Enter new appliances: ");
		String appliances = scan.nextLine();
		Recipe recipe = new Recipe(recipeName, ingredients, instructions, appliances);
		recipeManager.editRecipe(currentUser.getUsername(), recipe);
		System.out.println("Recipe edited successfully!");

	}

}
