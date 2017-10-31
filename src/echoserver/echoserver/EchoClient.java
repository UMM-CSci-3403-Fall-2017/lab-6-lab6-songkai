package echoserver;

import java.net.*;
import java.io.*;

public class EchoClient {

	public static void main(String[] args) {
		try {
			// open socket
			Socket socket = new Socket("127.0.0.1", 6013);

			// create streams and ready for bytes to come in
			InputStream stdardIn = System.in;
			InputStream serverIn = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			byte b;

			// we need to know the number of byte that read
			int number = stdardIn.read();

			while (number != -1) {
				b = (byte) number; // write byte to server
				output.write(b);
				b = (byte) serverIn.read();
				number = stdardIn.read(); // check for more input
				System.out.println(b);
			}

			// close streams
			stdardIn.close();
			serverIn.close();
			output.flush();
			output.close();
			socket.close();

		} catch (IOException ioe) {
			System.out.println("We caught an exception");
			System.err.println(ioe);
		}
	}
}