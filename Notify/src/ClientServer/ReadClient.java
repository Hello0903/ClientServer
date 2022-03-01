package ClientServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadClient extends Thread{
	private Socket client;
	
	public ReadClient(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		DataInputStream dis = null;
			try {
				dis = new DataInputStream(client.getInputStream());
				while(true) {
				String sms;
				sms = dis.readUTF();
				System.out.println(sms);
				}
			} catch (IOException e) {
				
				try {
					dis.close();
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}