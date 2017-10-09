package com.t99sdevelopment.philesd.locations;

import com.t99sdevelopment.philesd.cli.CLI;
import com.t99sdevelopment.philesd.cli.CLIStream;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Location {
	
	public String name;
	private Path path;
	private LocationWatcher watcher;
	private CLIStream stream;
	
	public Location(String name, String path) {

		this(name, Paths.get(path));
	
	}
	
	public Location (String name, Path path) {
		
		this.name = name;
		this.path = path;

		LocationManager.register(name, this);
		stream = new CLIStream(name, CLI.CLISTREAM_CONTEXT);
		
		try {
			
			watcher = new LocationWatcher(this.path, stream);
			
		} catch (IOException e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public Path getPath() {
		
		return path;
		
	}
	
	public void setPath(Path path) {
		
		this.path = path;
		
	}
	
	public void start() {
		
		watcher.runningState.set(true);
		System.out.println("Starting watch service for: '" + path.toString() + "'.");
		
	}
	
	public void stop() {
		
		watcher.runningState.set(false);
		System.out.println("Pausing watch service for: '" + path.toString() + "'.");
		
	}
	
	public void conditionalRun(boolean bool) {
		
		if (bool) {
			
			start();
			
		} else {
			
			stop();
			
		}
		
		
	}
	
}