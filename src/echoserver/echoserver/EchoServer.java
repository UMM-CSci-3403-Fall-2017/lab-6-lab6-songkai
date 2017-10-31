package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket sock = new ServerSocket(6013); // open socket
			byte b; // get a variable to store the byte
			while (true) {
				// where we know we get a request
				System.out.println("Got a request!");
				Socket client = sock.accept();

				// create two streams to represent output and input
				// respectively.
				OutputStream outputStreams = client.getOutputStream();
				InputStream inputStreams = client.getInputStream();
				// create a variable to store the number of bytes
				int bNum = inputStreams.read();

				// check byte one by one until the last one.
				while (bNum != -1) {
					b = (byte) bNum;
					outputStreams.write(b);
					bNum = inputStreams.read();
				}

				// close streams
				outputStreams.flush();
				client.close();
				inputStreams.close();
				outputStreams.close();
				sock.close();

			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}
