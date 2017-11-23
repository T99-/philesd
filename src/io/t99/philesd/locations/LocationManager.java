package io.t99.philesd.locations;

import java.util.HashMap;

public class LocationManager {
	
	private static HashMap<String, Location> locations = new HashMap<>();
	
	public static void register(String name, Location location) {
		
		locations.put(name, location);
		
	}
	
	public static Location get(String name) {
		
		return locations.get(name);
		
	}
	
	public static void remove(String name) {
		
		locations.remove(name);
		
	}
	
	public static void rename(String oldName, String newName) {
		
		locations.put(newName, locations.get(oldName));
		locations.remove(oldName);
		
	}
	
	public static String getPath(String name) {
	
		return locations.get(name).getPath().toString();
	
	}
	
	public static void getList() {
		
		for (String name: locations.keySet()) {
			
			System.out.println("Location Name: " + name);
			System.out.println("Location Path: " + locations.get(name).getPath().toString() + "\n");
			
		}
		
	}

}