package ClientServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WriteServer extends Thread{
	@Override
	public void run() {
		DataOutputStream dos = null;
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String sms = scanner.nextLine();
			 try {
				for(Socket item : Server.listUser) { 
				 dos = new DataOutputStream(item.getOutputStream());
				 dos.writeUTF(sms);
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
		
	}
}