package org.com.hbcookbook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;
public class UserManagerTest {
    private static final String TEST_USERS_FILE = "data/test_users.txt";
    private UserManager userManager;
    private File testFile;
    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary test file
        testFile = new File(TEST_USERS_FILE);
        testFile.getParentFile().mkdirs(); // Ensure the directory exists
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            bw.write("grace,1234\n");
            bw.write("seyi,abcd\n");
        }
        // Set the USERS_FILE path to the test file
        System.setProperty("users.file.path", TEST_USERS_FILE);
        userManager = new UserManager();
    }
    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        if (testFile.exists()) {
            testFile.delete();
        }
    }
    @Test
    public void testLoadUsers() {
        assertNotNull(userManager.getUser("grace"));
        assertNotNull(userManager.getUser("seyi"));
        assertNull(userManager.getUser("felix"));
    }
    @Test
    public void testAuthenticate() {
        assertTrue(userManager.authenticate("grace", "1234"));
        assertTrue(userManager.authenticate("seyi", "abcd"));
        assertFalse(userManager.authenticate("grace", "wrongpassword"));
        assertFalse(userManager.authenticate("seyi", "wrongpassword"));
        assertFalse(userManager.authenticate("felix", "1234"));
    }
    @Test
    public void testGetUser() {
        User grace = userManager.getUser("grace");
        assertNotNull(grace);
        assertEquals("grace", grace.getUsername());
        assertEquals("1234", grace.getPassword());
        User seyi = userManager.getUser("seyi");
        assertNotNull(seyi);
        assertEquals("seyi", seyi.getUsername());
        assertEquals("abcd", seyi.getPassword());
        User felix = userManager.getUser("felix");
        assertNull(felix);
    }
}






