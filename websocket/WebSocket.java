package com.t99sdevelopment.philesd.websocket;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSocket {
	
	public static final String WS_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
	
	private int port;
	
	private ServerSocket socket;
	public InputStream input;		// Where input is received from the client.
	private OutputStream output;	// Where output is written to the client.
	
	private WebSocketListener listener;
	
	public WebSocket(int port) {
	
		this.port = port;
		listener = new WebSocketListener(this);
	
	}
	
	public void establishServer() {
		
		try {
			
			socket = new ServerSocket(port);
			
			System.out.println("Server has started on 127.0.0.1:1200.\r\nWaiting for a connection...\r\n");
			
			Socket client = socket.accept();
			
			System.out.println("A client connected.\r\n");
			
			input = client.getInputStream();
			output = client.getOutputStream();
			
			String receivedClientHeaders = new Scanner(input, "UTF-8").useDelimiter("\\r\\n\\r\\n").next();
			
			// Get the WebSocket Key sent by the client.
			// This is extracted via regex from the client's sent headers.
			Pattern p = Pattern.compile("(?<=Sec-WebSocket-Key: )\\S+");
			Matcher m = p.matcher(receivedClientHeaders);
			String websocketReceivedKey = null;
			
			if (m.find()) {
				
				websocketReceivedKey = m.group(0);
			
			}
			
			String websocketAcceptKey;
			
			try {
				
				websocketAcceptKey = DatatypeConverter.printBase64Binary(MessageDigest.getInstance("SHA-1").digest((websocketReceivedKey + WS_GUID).getBytes("UTF-8")));
				
			} catch (NoSuchAlgorithmException e) {
				
				/*
				 * Honestly I'm not sure why this would throw an exception, it seems that there are better ways to
				 * implement this so that you *can't* choose an invalid/non-existant algorithm.
				 *
				 * Whatever.
				 *
				 * I'm only going to catch it, not handle it.
				 *
				 * The assignment below is just to make IntelliJ happy.
				 */
				
				websocketAcceptKey = "iwaswrong";
				
			}
			
			byte[] response = (
					"HTTP/1.1 101 Switching Protocols\r\n" +
					"Connection: upgrade\r\n" +
					"Upgrade: websocket\r\n" +
					"Sec-WebSocket-Accept: " + websocketAcceptKey + "\r\n" +
					"\r\n"
					).getBytes();
			
			output.write(response, 0, response.length);
			
			listener.provideInputStream(input);
			
		} catch (IOException e) {
			
			System.out.println("IOException occurred. You screwed something up. You might not have been able to get the port.");
			
		}
		
	}
	
	public void write(String text) {
	
		try {
			
			output.write(text.getBytes());
			
		} catch (IOException e) {
			
			System.err.println("Failed to write to the OutputStream.");
			
		}
	
	}
	
	public static void receive(String input) {
		
		Map<String, String> metainf = new HashMap<>();
		
		//metainf.put("FIN", )
		
	}
	
	public void close() {
		
		try {
			
			input.close();
			
		} catch (IOException e) {
			
			System.out.println("InputStream could not be closed.");
			System.err.println(e);
			
		}
		
		try {
			
			output.close();
			
		} catch (IOException e) {
			
			System.out.println("OutputStream could not be closed.");
			System.err.println(e);
			
		}
		
		try {
			
			socket.close();
			
		} catch (IOException e) {
			
			System.out.println("Socket could not be closed.");
			System.err.println(e);
			
		}
		
	}
	
}
