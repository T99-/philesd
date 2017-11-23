package io.t99.philesd.websocket;

import io.t99.philesd.util.NumberBaseConverter;

import java.io.IOException;
import java.io.InputStream;

public class WebSocketListener implements Runnable {
	
	private Thread listener;
	private WebSocket parent;
	private InputStream input;
	
	public WebSocketListener(WebSocket parent) {
		
		this.parent = parent;
		listener = new Thread(this, "listener");
		
	}
	
	public void provideInputStream(InputStream inputStream) {
		
		input = inputStream;
		listener.start();
		
	}
	
	@Override
	public void run() {
		
		int messageOver = WebSocketMessage.INCOMPLETE;
	
		while (true) {
			
			WebSocketMessage message = new WebSocketMessage();
			
			try {
				
				while (messageOver == WebSocketMessage.INCOMPLETE) {
					
					if (input != null && input.available() > 0) {

						messageOver = message.process(NumberBaseConverter.decToBin(input.read()));

					}

					if (messageOver == WebSocketMessage.ERROR) {

						parent.close();

					}
					
				}
				
				messageOver = WebSocketMessage.INCOMPLETE;
				
			} catch (IOException e) {
				
				System.out.println("Couldn't get the input stream...?");
				
			}
			
		}
	
	}
	
}