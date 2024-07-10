package org.com.hbcookbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	private static final String USERS_FILE = "data/users.txt";
	Map<String, User> users = new HashMap<>();

	public UserManager() {
		loadUsers();
	}

	private void loadUsers() {
		try (BufferedReader read = new BufferedReader(new FileReader(USERS_FILE))) {
			String line;
			while ((line = read.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					users.put(parts[0], new User(parts[0], parts[1]));
					read.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public boolean authenticate(String username, String password) {
		User user = users.get(username);
		return user != null && user.getPassword().equals(password);
	}

	public User getUser(String username) {
		return users.get(username);

	}

}
