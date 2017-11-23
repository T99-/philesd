package io.t99.philesd;

import io.t99.philesd.util.NumberBaseConverter;
import io.t99.philesd.websocket.WebSocket;

public class Philesd {
	
	public static void main(String[] args) {
		
//		for (byte b: "Hello".getBytes()) {
//
//			System.out.println(b);
//
//		}
		
		//WebSocket ws = new WebSocket(1200);
		
		//ws.establishServer();
		
		//ws.write("Hello");
		
		for (int i = 0; i < 8; i++) {
			
			System.out.println(i + "\t" + NumberBaseConverter.decToBin(i));
			
		}
		
	}
	
}