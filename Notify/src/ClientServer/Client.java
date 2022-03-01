package ClientServer;

import java.io.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext.Scope;

public class Client {
	private InetAddress host;
	private int post;
	public Client(InetAddress host, int post) {
		this.host = host;
		this.post = post;
	}
	
	private void execute() throws IOException {
		Socket client = new Socket(host,post);
		ReadClient read = new ReadClient(client);
		read.start();
		WriteClient write = new WriteClient(client);
		write.start();
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client(InetAddress.getLocalHost(), 9999);
		client.execute();
	}

}