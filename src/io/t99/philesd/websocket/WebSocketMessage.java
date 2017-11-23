package com.t99sdevelopment.philesd.websocket;

import com.t99sdevelopment.philesd.util.Binary;
import com.t99sdevelopment.philesd.util.NumberBaseConverter;

public class WebSocketMessage {
	
	/*
	 *
	 *	Frame format:
	​​ *
	 *	 0                   1                   2                   3
	 *	 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
	 *	+-+-+-+-+-------+-+-------------+-------------------------------+
	 *	|F|R|R|R| opcode|M| Payload len |    Extended payload length    |
	 *	|I|S|S|S|  (4)  |A|     (7)     |             (16/64)           |
	 *	|N|V|V|V|       |S|             |   (if payload len==126/127)   |
	 *	| |1|2|3|       |K|             |                               |
	 *	+-+-+-+-+-------+-+-------------+ - - - - - - - - - - - - - - - +
	 *	|     Extended payload length continued, if payload len == 127  |
	 *	+ - - - - - - - - - - - - - - - +-------------------------------+
	 *	|                               |Masking-key, if MASK set to 1  |
	 *	+-------------------------------+-------------------------------+
	 *	| Masking-key (continued)       |          Payload Data         |
	 *	+-------------------------------- - - - - - - - - - - - - - - - +
	 *	:                     Payload Data continued ...                :
	 *	+ - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - +
	 *	|                     Payload Data continued ...                |
	 *	+---------------------------------------------------------------+
	 *
	 */
	
	// The raw bytes of the message. This starts out empty and is appended to as more bytes are read from the
	// WebSocket's InputStream from the client.
	private Binary rawMessage = new Binary();
	
	// Assigned to numbers that have not yet been set.
	public static final int NOT_SET = -1;
	
	// The received message has not yet finished.
	public static final int INCOMPLETE = 0;
	
	// The received message has completed successfully.
	public static final int COMPLETE = 1;
	
	// The received message has indicated an error.
	public static final int ERROR = 2;
	
	/*
	 * These constants denote the payload size scheme that a message *can* used.
	 *
	 *	- PLS_SMALL		= 07 bits used to encode the payload size.
	 *	- PLS_MEDIUM	= 23 bits used to encode the payload size.
	 *	- PLS_LARGE		= 71 bits used to encode the payload size.
	 *
	 * 9 bits are then added for the FIN bit, the RSV1, RSV2, and RSV3 bits, the Opcode bits, and the mask bit which all
	 * precede the payload size in the WebSocket header.
	 */
	public static final int PLS_SMALL	= 16;
	public static final int PLS_MEDIUM	= 32;
	public static final int PLS_LARGE	= 80;
	
	// The finality marker for the message. If this is true, this is the last message in a series. Singlet messages
	// are marked with `fin = true` as well.
	private Boolean fin;
	
	// I'm not 100% sure what these are for yet... something to do with extensions maybe.
	private Boolean rsv1;
	private Boolean rsv2;
	private Boolean rsv3;
	
	/*
	 * The OpCode for the message. Available codes listed below.
	 *
	 *	- 0x0 (dec 00): Continuation Frame
	 *	- 0x1 (dec 01): Text Frame
	 *	- 0x2 (dec 02): Binary Frame
	 *	- 0x3 (dec 03): [Reserved for further non-control frames...]
	 *	- 0x4 (dec 04): [Reserved for further non-control frames...]
	 *	- 0x5 (dec 05): [Reserved for further non-control frames...]
	 *	- 0x6 (dec 06): [Reserved for further non-control frames...]
	 *	- 0x7 (dec 07): [Reserved for further non-control frames...]
	 *	- 0x8 (dec 08): Close Connection
	 *	- 0x9 (dec 09): Ping!
	 *	- 0xA (dec 10): Pong!
	 *	- 0xB (dec 11): [Reserved for further control frames...]
	 *	- 0xC (dec 12): [Reserved for further control frames...]
	 *	- 0xD (dec 13): [Reserved for further control frames...]
	 *	- 0xE (dec 14): [Reserved for further control frames...]
	 *	- 0xF (dec 15): [Reserved for further control frames...]
	 */
	private int opcode = NOT_SET;
	
	// The mask marker for the message. If this is true, the message is masked, as is usually (and as should be) the
	// case with client-to-server communication.
	private Boolean masked;
	
	// This holds one of the PLS_XXX constants from above. It denotes which payload size scheme this message's header
	// uses.
	private int	payloadLengthSize = NOT_SET;
	
	// The decimal value found from the first seven bits that can be used to encode the payload size.
	private int payloadLengthPlaceholder = NOT_SET;
	
	// The length of the payload.
	private long payloadLengthBytes = NOT_SET;
	
	// The masking key to decode the payload.
	private Binary maskingKey;
	
	// The size of the WebSocket 'headers'.
	private int headerSize = NOT_SET;
	
	// The actual raw data of the payload, with the metadata stripped.
	// payload = rawMessage - (fin + RSV# + opcode + masked + payloadLengthBytes + maskingKey)
	private Binary payload;
	
	// Whether or not the message is complete.
	private int complete = INCOMPLETE;
	
	public int process(Binary bin) {
	
		rawMessage.append(bin);
		
		if (fin == null && rawMessage.length() >= 1) {
			
			fin = rawMessage.getBit(0);
			
		}
		
		if (rsv1 == null && rawMessage.length() >= 2) {
			
			rsv1 = rawMessage.getBit(1);
			
		}
		
		if (rsv2 == null && rawMessage.length() >= 3) {
			
			rsv2 = rawMessage.getBit(2);
			
		}
		
		if (rsv3 == null && rawMessage.length() >= 4) {
			
			rsv3 = rawMessage.getBit(3);
			
		}
		
		if (opcode == NOT_SET && rawMessage.length() >= 8) {
			
			opcode = NumberBaseConverter.binToDec(new Binary(rawMessage, 4, 8));
			
		}
		
		if (masked == null && rawMessage.length() >= 9) {
			
			masked = rawMessage.getBit(8);
			if (masked != true) {
				
				complete = ERROR;
				
			}
			
		}
		
		if (payloadLengthPlaceholder == NOT_SET && rawMessage.length() >= PLS_SMALL) {
			
			payloadLengthPlaceholder = NumberBaseConverter.binToDec(new Binary(rawMessage, 9, PLS_SMALL));
			
		}
		
		if (payloadLengthBytes == NOT_SET && payloadLengthPlaceholder <= 125 && payloadLengthPlaceholder != NOT_SET) {
			
			payloadLengthSize = PLS_SMALL;
			payloadLengthBytes = payloadLengthPlaceholder;
			headerSize = PLS_SMALL; // Without the masking key.
			
		}
		
		if (payloadLengthBytes == NOT_SET && payloadLengthPlaceholder == 126 && rawMessage.length() >= PLS_MEDIUM) {
			
			payloadLengthSize = PLS_MEDIUM;
			payloadLengthBytes = NumberBaseConverter.binToDec(new Binary(rawMessage, 9, PLS_MEDIUM));
			headerSize = PLS_MEDIUM; // Without the masking key.
			
		}
		
		if (payloadLengthBytes == NOT_SET && payloadLengthPlaceholder == 127 && rawMessage.length() >= PLS_LARGE) {
			
			payloadLengthSize = PLS_LARGE;
			payloadLengthBytes = NumberBaseConverter.binToDec(new Binary(rawMessage, 9, PLS_LARGE));
			headerSize = PLS_LARGE; // Without the masking key.
			
		}
		
		if (masked != null && masked && maskingKey == null && rawMessage.length() >= headerSize + 32) {
			
			maskingKey = new Binary(rawMessage, headerSize, headerSize + 32);
			headerSize += 32; // Now it includes the masking key.
			
		}
		
		if ((rawMessage.length() - headerSize) == payloadLengthBytes * 8 && maskingKey != null) {
			
			payload = new Binary(rawMessage, headerSize);
			
			Binary encodedPayload = payload;	// This will hold the masked version of the payload.
			payload = new Binary();				// The payload variable can now hold the unmasked version.
			
			// Here's where we unmask the payload.
			Binary[] maskingKeyOctets = maskingKey.getBinaryOctetArray();
			Binary[] payloadOctets = encodedPayload.getBinaryOctetArray();
			
			for (int octet = 0; octet < payloadOctets.length; octet++) {
			
				payload.append(Binary.xor(payloadOctets[octet], maskingKeyOctets[octet % 4]));
			
			}
			
			complete = COMPLETE;
			
			for (Binary binary: payload.getBinaryOctetArray()) {
				
				System.out.print((char) NumberBaseConverter.binToDec(binary));
				
			}
			
			System.out.println("\r\n");
			
			for (Binary binary: payload.getBinaryOctetArray()) {
				
				System.out.println(NumberBaseConverter.binToDec(binary));
				
			}
			
			System.out.print("\r\n");
			
		}
		
		return complete;
	
	}
	
	@Override
	public String toString() {
		
		String output = "";
		
		output += "FIN:\t\t\t"			+ fin							+ "\r\n";
		output += "RSV1:\t\t\t"			+ rsv1							+ "\r\n";
		output += "RSV2:\t\t\t"			+ rsv2							+ "\r\n";
		output += "RSV3:\t\t\t"			+ rsv3							+ "\r\n";
		output += "OpCode:\t\t\t"		+ opcode						+ "\r\n";
		output += "Masked:\t\t\t"		+ masked						+ "\r\n";
		output += "Payload Length:\t"	+ payloadLengthBytes			+ "\r\n";
		output += "Masking Key:\t"		+ maskingKey					+ "\r\n";
		output += "Payload:\r\n"		+ payload.toMultilineString()	+ "\r\n";
		
		return output;
		
	}

}
