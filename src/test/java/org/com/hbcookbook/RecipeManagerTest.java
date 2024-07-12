package org.com.hbcookbook;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
class RecipeManagerTest {
    private RecipeManager recipeManager;
    private static final String TEST_USER = "testuser";
    @BeforeEach
    void setUp() throws IOException {
        // Setup a test recipes directory
        Files.createDirectories(Paths.get("data/recipes/" + TEST_USER));
        recipeManager = new RecipeManager();
    }
    @AfterEach
    void tearDown() throws IOException {
        // Clean up the test recipes directory
        Files.walk(Paths.get("data/recipes/" + TEST_USER))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Test Recipe", "Test Ingredients", "Test Instructions", "Test Appliances");
        recipeManager.addRecipe(TEST_USER, recipe);
        File recipeFile = new File("data/recipes/" + TEST_USER + "/Test Recipe.txt");
        assertTrue(recipeFile.exists());
    }
    @Test
    void testGetRecipes() {
        Recipe recipe = new Recipe("Test Recipe", "Test Ingredients", "Test Instructions", "Test Appliances");
        recipeManager.addRecipe(TEST_USER, recipe);
        List<Recipe> recipes = recipeManager.getRecipes(TEST_USER);
        assertFalse(recipes.isEmpty());
        assertEquals("Test Recipe", recipes.get(0).getRecipeName());
    }
    @Test
    void testDeleteRecipe() {
        Recipe recipe = new Recipe("Test Recipe", "Test Ingredients", "Test Instructions", "Test Appliances");
        recipeManager.addRecipe(TEST_USER, recipe);
        recipeManager.deleteRecipe(TEST_USER, "Test Recipe");
        File recipeFile = new File("data/recipes/" + TEST_USER + "/Test Recipe.txt");
        assertFalse(recipeFile.exists());
    }
    @Test
    void testEditRecipe() {
        Recipe recipe = new Recipe("Test Recipe", "Test Ingredients", "Test Instructions", "Test Appliance");
        recipeManager.addRecipe(TEST_USER, recipe);
        Recipe updatedRecipe = new Recipe("Test Recipe", "Updated Ingredients", "Updated Instructions", "Updated Appliances");
        recipeManager.editRecipe(TEST_USER, updatedRecipe);
        List<Recipe> recipes = recipeManager.getRecipes(TEST_USER);
        assertEquals("Updated Ingredients", recipes.get(0).getIngredients());
    }
}

