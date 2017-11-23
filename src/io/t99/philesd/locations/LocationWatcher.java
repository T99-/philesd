package io.t99.philesd.locations;

import io.t99.philesd.cli.CLIStream;
import io.t99.philesd.util.SingleSend;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.file.StandardWatchEventKinds.*;

public class LocationWatcher implements Runnable {
	
	private WatchService watcher;
	private Path path;
	private CLIStream stream;
	protected AtomicBoolean runningState = new AtomicBoolean(true);
	private boolean validPath = true;
	private boolean validityCheck = true;
	
	protected LocationWatcher(Path path, CLIStream stream) throws IOException {
		
		(new Thread(new LocationWatcher(path, stream, ""))).start();
		
	}
	
	private LocationWatcher(Path path, CLIStream stream, String placeholder) throws IOException {
		
		this.path = path;
		this.stream = stream;
		this.watcher = FileSystems.getDefault().newWatchService();
		this.path.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
		
	}
	
	public void run() {
		
		SingleSend.printlnToStream("Reached step: 1", stream);
		
		while(true) {
			
			SingleSend.printlnToStream("Reached step: 2", stream);
			
			if(runningState.get() && validPath) {
				
				SingleSend.println("Reached step: 3");
				
				WatchKey key;
				
				try {
					
					Thread.sleep(200);
					key = watcher.take();
					
				} catch (InterruptedException e) {
					
					return;
					
				}
				
				for (WatchEvent<?> event: key.pollEvents()) {
					
					Path filename;
					String eventKind;
					
					WatchEvent.Kind kind = event.kind();
					
					if (kind == OVERFLOW) {
						
						System.out.println("OVERFLOW event received.");
						eventKind = "OVR";
						continue;
						
					} else if (kind == ENTRY_CREATE) {
						
						eventKind = "CRT";
						
					} else if (kind == ENTRY_MODIFY) {
						
						eventKind = "CHG";
						
					} else if (kind == ENTRY_DELETE) {
						
						eventKind = "DLT";
						
					} else {
						
						eventKind = "ERR";
						
					}
					
					filename = ((WatchEvent<Path>)event).context();
					
					System.out.println(eventKind + " - " + filename);
					
				}
				
				validityCheck = key.reset();
				
			}
			
			if (!validityCheck) {
				
				break;
				
			}
			
		}
		
	}

}